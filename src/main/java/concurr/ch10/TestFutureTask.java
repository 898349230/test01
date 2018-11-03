package concurr.ch10;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class TestFutureTask {

	private static final ConcurrentHashMap<Object, Future<String>> taskCache = new ConcurrentHashMap<>();
	
	public static void main(String[] args) {
		String taskResult = executionTask("future task.....");
		System.out.println(taskResult);
	}
	
	/**
	 * 当一个线程需要等待另一个线程把某个任务执行完后它才能继续执行，此时可以使用FutureTask。
	 * 
	 * 假设有多个线程执行若干任务，每个任务最多只能被执行一次。
	 * 当多个线程试图同时执行同一个任务时，只允许一个线程执行任务，其他线程需要等待这个任务执行完后才能继续执行。
	 * 
	 * @param taskName
	 * @return
	 */
	public static String executionTask(final String taskName){
		while(true) {
			Future<String> future = taskCache.get(taskName);
			if(null == future) {
				Callable<String> task = new Callable<String>() {
					@Override
					public String call() throws Exception {
						return taskName;
					}
				};
				
				FutureTask<String> futureTask =  new FutureTask<>(task);
				future = taskCache.putIfAbsent(taskName, futureTask);
				if(null == future) {
					future = futureTask;
					futureTask.run();
				}
			}
			
			try {
				return future.get();
			} catch (InterruptedException | ExecutionException e) {
				taskCache.remove(taskName, future);
				e.printStackTrace();
			}
			
		}
	}
}
