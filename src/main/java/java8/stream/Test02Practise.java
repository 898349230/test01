package java8.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 使用流  练习
 * @author lzx-t050
 *
 */
public class Test02Practise {

	public static void main(String[] args) {
		
		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario","Milan");
		Trader alan = new Trader("Alan","Cambridge");
		Trader brian = new Trader("Brian","Cambridge");
		
		List<Transaction> transactions = Arrays.asList(
					new Transaction(brian, 2011, 300),
					new Transaction(raoul, 2012, 1000),
					new Transaction(raoul, 2011, 400),
					new Transaction(mario, 2012, 710),
					new Transaction(mario, 2012, 700),
					new Transaction(alan, 2012, 950)
					);
		
		// (1) 找出2011年发生的所有̓易，并按̓易额排序（从低到高）
		List<Transaction> list1 = transactions.stream()
			.filter(t -> t.getYear() == 2011)
			.sorted(Comparator.comparing(Transaction::getValue))
			.collect(Collectors.toList());
		System.out.println("(1): " + list1);
		
		// (2) ̓交易员都在哪些不同的城市工作过？
		List<String> list2 = transactions.stream()
			.map(t -> t.getTrader().getCity())
			.distinct()
			.collect(Collectors.toList());
		Set<String> set2 = transactions.stream()
				.map(t -> t.getTrader().getCity())
				.collect(Collectors.toSet());
		System.out.println("(2) : " + list2);
		System.out.println("(2) : " + set2);
		
//		(3) 查找所有来自于剑桥的̓交易员，并按姓名排序。
		List<Trader> list3 = transactions.stream()
			.map(t -> t.getTrader())
			.filter(t -> t.getCity().equals("Cambridge"))
			.distinct()
			.sorted(Comparator.comparing(Trader::getName))
			.collect(Collectors.toList());
		System.out.println("(3) : " + list3);
		
//		(4) 返回所有̓易员的姓名字符串，按名字字母顺序排序。
		String nameStr = transactions.stream()
			.map(t -> t.getTrader().getName())
			.sorted()
//			.reduce("", (a, b) -> a + b); // 所有字符串都被反复拼接，每次迭代的时候都要建立一个新的String对象
			.collect(Collectors.joining()); // 内部使用stringBuilder，比 reduce("", (a, b) -> a + b) 效率高
		System.out.println("(4) : " + nameStr);
		
//		(5) 有没有̓易员是在米兰工作的？
		boolean hasMilan = transactions.stream()
				.anyMatch(t -> t.getTrader().getCity().equals("Milan"));
		System.out.println("(5) 有没有̓易员是在米兰工作的 : " + hasMilan);
		
//		(6) 打印生活在剑桥的̓易员的所有̓交易额。
		transactions.stream()
			.filter(t -> t.getTrader().getCity().equals("Cambridge"))
			.map(t -> t.getValue())
			.forEach(value -> System.out.println("交易额：" + value));
		
//		(7) 所有̓交易中，最高的交̓易额是多少？
		Optional<Integer> maxOp = transactions.stream()
			.map(t -> t.getValue())
			.reduce(Integer::max);
		maxOp.ifPresent(max -> System.out.println("(7)最大交易额：" + max));
		
//		(8) 找到̓交易额最小的̓交易。
		Optional<Transaction> minOp = transactions.stream()
			.reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);
		minOp.ifPresent(t -> System.out.println("(8,方法1) 交易额最小的交易：" + t));
		 
		Optional<Transaction> minOp2 = transactions.stream()
		 // 流支持min和max方法，它们可以接受一个Comparator作为参数，指定计算最小或最大值时要比较哪个键值
			.min(Comparator.comparing(Transaction::getValue)); 
		minOp2.ifPresent(t -> System.out.println("(8,方法2) 交易额最小的交易：" + t));
		
		/** 数值流练习  生成勾股数  */
		Stream<int[]> stream1 = IntStream.rangeClosed(1, 100).boxed()
			//	对每个给定的a值，创建一个三元数流。要是把a的值映射到三元数流的话，就会得到一个由流构成的流。 
			// flatMap方法在做映射的同时，还会把所有生成的三元数流扁平化成一个流。这样你就得到了一个三元数流。
			.flatMap(a -> IntStream.rangeClosed(a, 100)
							.filter(b -> ((Math.sqrt(a * a + b * b)) % 1 == 0))
							// mapToObj方法会返回一个对象值流   (如果使用IntStream的map()方法，则只会返回一个int类型的值，不会返回int[])    
							.mapToObj(b -> new int[] {a, b, (int) Math.sqrt(a * a + b * b)})
					);
		
		List<int[]> l1 = stream1
				.limit(10)
				.collect(Collectors.toList());
		// 先产生三元数，在过滤
		List<double[]> l2 = IntStream.rangeClosed(1,  100).boxed()
				.flatMap(a -> IntStream.rangeClosed(a, 100)
								.mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
								.filter(d -> d[2] % 1 ==0)
						)
				.collect(Collectors.toList());
		
		System.out.println("勾股数1：");
		for (int[] is : l1) {
			System.out.print("[" + is[0] + "," + is[1] + "," + is[2] + "]");
			System.out.print("  ");
		}
		System.out.println("勾股数2：");
		for (double[] is : l2) {
			System.out.print("[" + is[0] + "," + is[1] + "," + is[2] + "]");
			System.out.print("  ");
		}
		
		
		
	}
}
