package java8.stream3;

import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallelStreams {

	/**
	 * 顺序流 方式
	 * iterate生成的是装箱的对象，必须拆箱成数字才能求和
	 * @param n
	 * @return
	 */
	public static long SequentialSum(long n) {
		return Stream.iterate(0l, i -> i + 1).limit(n).reduce(0l, Long::sum);
	}
	
	/**
	 * 并行流 方式
	 * iterate生成的是装箱的对象，必须拆箱成数字才能求和
	 * @param n
	 * @return
	 */
	public static long ParallelSum(long n) {
		return Stream.iterate(0l, i -> i + 1).limit(n).parallel().reduce(0l, Long::sum);
	}
	
	/**
	 * 使用 rangeClosed，顺序流<br>
	 * rangeClosed直接产生原始类型的long数字，没有装箱拆箱的开销
	 * rangeClosed生成的数字范围很容易拆分为独立的小块。例如，1~20的范围可分为1~5、 6~10、 11~15和16~20。
	 * @param n
	 * @return
	 */
	public static long rangeSum(long n) {
		long reduce = LongStream.rangeClosed(0l, n).reduce(0l, Long::sum);
		return reduce;
	}
	
	/**
	 * 使用 rangeClosed，并行流<br>
	 * rangeClosed直接产生原始类型的long数字，没有装箱拆箱的开销
	 * rangeClosed生成的数字范围很容易拆分为独立的小块。例如，1~20的范围可分为1~5、 6~10、 11~15和16~20。
	 * @param n
	 * @return
	 */
	public static long rangeParallelSum(long n) {
		long reduce = LongStream.rangeClosed(0l, n).parallel().reduce(0l, Long::sum);
		return reduce;
	}
	
	/**
	 * 旧的Java遍历方式
	 * @param n
	 * @return
	 */
	public static long IterativeSum(long n) {
		long sum = 0;
		for(long i = 1l; i <= n ; i++) {
			sum += i;
		}
		return sum;
	}
	
	/**
	 * 使用并行流
	 * @param n
	 * @return
	 */
	public static long sideEffectParallelSum(long n) {
		Accumulator accumulator = new Accumulator();
		LongStream.rangeClosed(1, n).parallel().forEach(accumulator::add);
		return accumulator.total;
	}

	/**
	 * 顺序流
	 * @param n
	 * @return
	 */
	public static long sideEffectSum(long n) {
		Accumulator accumulator = new Accumulator();
		LongStream.rangeClosed(1, n).forEach(accumulator::add);
		return accumulator.total;
	}

	public static class Accumulator {
		public long total = 0;
		public void add(long value) {
			total += value;
		}
	}
}
