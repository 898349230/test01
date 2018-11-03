package java8.stream;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test03 {
	
	public static void main(String[] args) {
		
		/** 创建流 */
		// 由值创建：
		Stream<String> s1 = Stream.of("阿布", "ab", "啊啊", "哈哈");
		// 空流：
		Stream<Object> empty = Stream.empty();
		// 数组：
		int[] numbers = {1, 2, 5, 6, 7};
		int sum1 = Arrays.stream(numbers).sum();
		System.out.println("sum1: " + sum1);
		
		/** 文件生成流： */
		// java.nio.file.Files中的很多静态方法都会返回一个流
		// Files.lines，它会返回一个由指定文件中的各行构成的字符串流
		try {
			Stream<String> lines = Files
					.lines(Paths.get("C:\\Users\\lzx-t050\\Desktop\\testWord.txt"), Charset.defaultCharset());
			long count = lines.flatMap(line -> Arrays.stream(line.split(" ")))
					.distinct()
					.count();
			System.out.println("count : " + count);
			lines.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/** 函数生成流：创建无限流  Stream.iterate和Stream.generate */ 
		//  Stream.iterate
		System.out.println("Stream.iterate :  ");
		Stream.iterate(0, n -> n + 2) // 产生偶数， 能够无限制的生成
			.limit(10) // 限制为10次
			.forEach(n -> System.out.print(n + "\t"));
		
		System.out.println("\n 斐波那契数列1： ");
		Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0]+t[1]})
			.limit(10)
			.forEach(t -> {System.out.print("[" + t[0] + "," + t[1] + "]\t");});
		
		// Stream.generate : 它接受一个Supplier<T>类型的Lambda提供新的值
		// Math::random 供应源是无状态的:它不会在任何地方记录任何值，以备以后计算使用
		System.out.println("\nStream.generate : ");
		Stream.generate(Math::random)
			.limit(5) // limit方法显式限制流的大小
			.forEach(n -> System.out.print(n + "\t"));
		
		IntStream twos = IntStream.generate(new IntSupplier() {
			@Override
			public int getAsInt() {
				return 2;
			}
		});
		System.out.println("IntStream.generate : ");
		twos.limit(3).forEach(n -> System.out.print(n + "\t"));
		
		System.out.println("\n 斐波那契数列2： ");
		// 创建存储状态的供应源，它可以修改状态，并在为流生成下一个值时使用
		// 在并行代码中使用有状态的供应源是不安全的，应尽量避免使用
		IntSupplier fib = new IntSupplier() {
//			匿名类可以通过字段定义状态，而状态又可以用getAsInt方法来修改
			private Integer previous = 0;
			private Integer current = 1;
			@Override
			public int getAsInt() {
				Integer oldPrevious = this.previous;
				Integer nextValue = this.previous + this.current;
				this.previous = this.current;
				this.current = nextValue;
				return oldPrevious;
			}
		};
		// fib 供应源是有状态的
		IntStream.generate(fib)
			.limit(10)
			.forEach(t -> System.out.print(t + "\t"));
		
	}
}
