package java8.stream2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * 自定义 Collector收集器
 * @author 
 *
 */
public class PrimNumbersCollector implements 
		Collector<Integer, // 流中元素类型
			Map<Boolean, List<Integer>>, // 累加器类型
			Map<Boolean, List<Integer>>>{// collect 操作的结果类型

	/**
	 *  supplier方法会返回一个在调用时创建累加器的函数：
	 *  
	 *  一个有两个空List的Map开始收集过程
	 */
	@Override
	public Supplier<Map<Boolean, List<Integer>>> supplier() {
		return () -> new HashMap<Boolean, List<Integer>>(){{
			put(true, new ArrayList<Integer>());
			put(false, new ArrayList<Integer>());
		}};
	}

	/**
	 * 定义了如何收集流中元素的逻辑
	 */
	@Override
	public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
		return (Map<Boolean, List<Integer>> acc, Integer candidate) -> {
			// 调用了isPrime方法，将待测试是否为质数的数以及迄今找到的质数列表（也就是累积Map中true键对应的值）传递给它。
			// 这次调用的结果随后被用作获取质数或非质数列表的键，这样就可以把新的被测数添加到恰当的列表中。
			acc.get(this.isPrime(acc.get(true), candidate)) // 根据isPrime 方法获取质数与非质数的列表
				.add(candidate); // 将被测数放入对应的列表
		};
	}

	/**
	 * 让收集器并行工作
	 * 实际上这个收集器是不能并行使用的，因为该算法本身是顺序的
	 */
	@Override
	public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
		return (Map<Boolean, List<Integer>> map1,
				Map<Boolean, List<Integer>> map2) -> {
					map1.get(true).addAll(map2.get(true));
					map1.get(false).addAll(map2.get(false));
					return map1;
			};
	}

	/**
	 * accumulator正好就是收集器的结果，
	 * 用不着进一步转换，那么finisher方法就返回identity函数
	 */
	@Override
	public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
		return Function.identity();
	}

	/**
	 * 返回一个不可变的Characteristics集合，定义了收集器的行为
	 * 是一个枚举内的值：
	 * UNORDERED———归约结果不受流中项目的遍历和累积顺序的影响
	 * CONCURRENT——accumulator函数可以从多个线程同时调用，且该收集器可以并行归约流。如果收集器没有标为UNORDERED，那它仅在用于无序数据源时才可以并行归约
	 * IDENTITY_FINISH——这表明完成器方法返回的函数是一个恒等函数，可以跳过。
	 * 这种情况下，累加器对象将会直接用作归约过程的最终结果。这也意味着，将累加器A不加检查地转换为结果R是安全的
	 * 
	 * 
	 * 这个收集器既不是IDENTITY_FINISH，也不是UNORDERED的，
	 * 而是CONCURRENT，因为质数是按照顺序出现的
	 * 
	 */
	@Override
	public Set<Characteristics> characteristics() {
		return  Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.IDENTITY_FINISH));
	}
	
	/** 
	 * 判断candidate是否为质数，primes为被测试数之前的质数
	 * 
	 */
	public boolean isPrime(List<Integer> primes, int candidate) {
		int candidateRoot = (int) Math.sqrt((double) candidate);
		return takeWhile(primes, i -> i <= candidateRoot)
				.stream()
				.noneMatch(p -> candidate % p == 0);
	}

	/** 用于优化isPrime方法，只用不大于被测数平方根的质数去测试 */
	public <A> List<A> takeWhile(List<A> list, Predicate<A> p) {
		int i = 0;
		for (A item : list) {
			if (!p.test(item)) { // 检查列表中的元素是否符合谓词
				return list.subList(0, i); // 如果不符合，截断list
			}
			i++;
		}
		return list;  // 返回List
	}
}
