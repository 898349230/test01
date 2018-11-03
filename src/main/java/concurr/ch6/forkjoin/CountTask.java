package concurr.ch6.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * RecursiveAction：用于没有返回结果的任务。
 * RecursiveTask：用于有返回结果的任务。
 * 
 * @author 
 *
 */
public class CountTask extends RecursiveTask<Integer>{

	private static int THRESHOLD = 2;
	private int start;
	private int end;
	
	public CountTask(int start, int end) {
		this.start = start;
		this.end = end; 
	}
	
	@Override
	protected Integer compute() {
		int sum = 0;
		boolean canCompute = (end - start) <= THRESHOLD;
		// 任务足够小就执行任务
		if(canCompute) {
			for(int i = start; i <= end; i++) {
				sum += i;
			}
//			throw new IllegalArgumentException();
		}else {
			// 任务太大，分割任务
			int middle = (start + end)/2;
			CountTask leftTask = new CountTask(start, middle);
			CountTask rightTask = new CountTask(middle + 1, end);
			// 执行子任务 ，每个子任务在调用fork方法时， 又会进入compute方法
			ForkJoinTask<Integer> fork = leftTask.fork();
			rightTask.fork();
			// 等待子任务完成，获取结果
			Integer leftResult = leftTask.join();
			Integer rightResult = rightTask.join();
			// 合并结果
			sum = leftResult + rightResult;
		}
		
		return sum;
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		// 创建 1+2+3+...+100 的任务
		CountTask task = new CountTask(1, 100);
		ForkJoinTask<Integer> submit = forkJoinPool.submit(task);
		Integer result = submit.get();
		
//		ForkJoinTask在执行的时候可能会抛出异常，但是我们没办法在主线程里直接捕获异常，
//		所以ForkJoinTask提供了isCompletedAbnormally()方法来检查任务是否已经抛出异常或已经被
//		取消了，并且可以通过ForkJoinTask的getException方法获取异常
		
		if(task.isCompletedAbnormally()) {
			System.out.println(task.getException());
		}
		
		System.out.println(result);
		
	}

}
