package java8.stream3;

import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;
import java.util.stream.LongStream;

/**
 * 并行流
 * 
 * @author
 *
 */
public class Test01 {

	public static void main(String[] args) {

//		System.out.println(
//				"Sequential sum done in:" + measureSumPerf(ParallelStreams::SequentialSum, 10_000_000) + " msecs");
//
//		System.out
//				.println("Parallel sum done in:" + measureSumPerf(ParallelStreams::ParallelSum, 10_000_000) + " msecs");
//
//		System.out.println(
//				"Iterative sum done in:" + measureSumPerf(ParallelStreams::IterativeSum, 10_000_000) + " msecs");
//
//		System.out.println("range sum done in:" + measureSumPerf(ParallelStreams::rangeSum, 10_000_000) + " msecs");
//
//		System.out.println("rangeParallel sum done in:" + measureSumPerf(ParallelStreams::rangeParallelSum, 10_000_000)
//				+ " msecs");
//		
//		// 使用并行流 每次执行结果都是不一样的，有问题
//		for(int i = 0 ; i < 10 ;i++) {
//			System.out.println("SideEffect parallel sum done is : " + measureSum(ParallelStreams::sideEffectParallelSum, 10_000_000L));
//		}
//		// 顺序流没有问题
//		for(int i = 0 ; i < 10 ;i++) {
//			System.out.println("SideEffect sequenial sum done is : " + measureSum(ParallelStreams::sideEffectSum, 10_000_000L));
//		}
//		
		/** 流的数据源和可分解性 */
		// ArrayList 极佳
		// LinkedList 差
		// IntStream.range 极佳
		// Stream.iterate 差
		// HashSet 好
		// TreeSet 好
		
		/**使用自定义的 分支/合并 进行求和 */
		long sum = forkJoinSum(1_000_000);
		System.out.println(" 自定义分支/合并  求和  ： " + sum);

	}

	/**
	 * 计量时间用
	 * 
	 * @param adder
	 * @param n
	 * @return
	 */
	public static long measureSumPerf(Function<Long, Long> adder, long n) {
		long fastest = Long.MAX_VALUE;
		for (int i = 0; i < 10; i++) {
			long start = System.nanoTime();
			long sum = adder.apply(n);
			long duration = (System.nanoTime() - start) / 1_000_000;
			// System.out.println("Result: " + sum);
			if (duration < fastest)
				fastest = duration;
		}
		return fastest;
	}

	private static Long measureSum(Function<Long, Long> adder, long l) {
		return adder.apply(l);
	}

	/**
	 * 计算数组内元素的和<br>
	 * 使用自定义的 分支/合并 进行求和
	 * 并行求和
	 * @param n
	 * @return
	 */
	public static long forkJoinSum(long n) {
		long[] number = LongStream.rangeClosed(0l, n).toArray();
		ForkJoinSumCalculator task = new ForkJoinSumCalculator(number);
		Long result = new ForkJoinPool().invoke(task);
		return result;
	}
	
}
