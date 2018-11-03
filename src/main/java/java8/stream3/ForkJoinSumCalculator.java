package java8.stream3;

import java.util.concurrent.RecursiveTask;

/**
 * 分支/合并 
 * RecursiveTask<Long> 中 泛型 Long
 * 是并行化任务（以及所有子任务）产生的结果类型，
 * 或者如果任务不返回结果，则是RecursiveAction类型
 * 
 * 分支/合并框架的目的是以递归方式将可以并行的任务拆分成更小的任务，然后将每个子任务的结果合并起来生成整体结果。
 * 
 * 使用分支/合并框架求和
 * @author 
 */
public class ForkJoinSumCalculator extends RecursiveTask<Long>{

	// 要计算的数组
	private final long[] number;
	// 子任务处理的数组的起始位置
	private final int start;
	// 子任务处理的数组的结束位置
	private final int end;
	
	// 不再将任务分解为子任务的数组的大小
	public static final long THRESHOLD = 10_000;
	
	// 构造方法，用于创建主任务
	public ForkJoinSumCalculator(long[] number) {
		this(number, 0, number.length);
	}
	
	// 私有构造方法，用于已递归方式为主任务构造子任务用
	private ForkJoinSumCalculator(long[] number, int start, int end) {
		this.number = number;
		this.start = start;
		this.end = end;
	}

	@Override
	protected Long compute() {
		// 该任务用于计算求和的  数组的大小
		int length = end - start;
		if(length < THRESHOLD) {
			// 数组大小预定值，不再分隔为小任务，进行计算
			return this.computeSequentially();
		}
		
		// 生成 新的子任务 为数组的前一半求和
		ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(number, start, start + length/2);
		// 利用另一个ForkJoinPool线程异步执行创建一个新的子任务
		leftTask.fork();
		// 生成 新的子任务 为数组的后一半求和
		ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(number, start + length/2, end);
		// 同步执行第二个子任务，有可能允许进一步递归划分
		Long rightResult = rightTask.compute();
		// 获取第一个子任务的结果，如果未完成就等待
		Long leftResult = leftTask.join();
		
		// 两个结果相加
		return leftResult + rightResult;
	}

	/**
	 * 当子任务不再分割为更小的子任务时调用该方法进行计算
	 * @return
	 */
	private long computeSequentially() {
		long sum = 0;
		for(int i = start ; i < end; i++) {
			sum += number[i];
		}
		return sum;
	}
}
