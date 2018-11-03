package concurr.ch10;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

public class TestExecutors {

	public static void main(String[] args) {
		
	}

	/**
	 *  Executors可以创建3种类型的ThreadPoolExecutor：
	 *  SingleThreadExecutor、FixedThreadPool和CachedThreadPool。
	 */
	public static void testThreadPoolExecutor() {
		
		// 创建使用固定线程数
//		FixedThreadPool适用于为了满足资源管理的需求，而需要限制当前线程数量的应用场景，它适用于负载比较重的服务器。
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(5);
		
		// SingleThreadExecutor适用于需要保证顺序地执行各个任务；并且在任意时间点，不会有多个线程是活动的应用场景。
		ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
		
		// CachedThreadPool是大小无界的线程池，适用于执行很多的短期异步任务的小程序，或者是负载较轻的服务器。
		ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
		
	}
	
	/**
	 * Executors可以创建2种类型的ScheduledThreadPoolExecutor
	 * 
	 * ScheduledThreadPoolExecutor。包含若干个线程的ScheduledThreadPoolExecutor。
	 * SingleThreadScheduledExecutor。只包含一个线程的ScheduledThreadPoolExecutor。
	 * 
	 */
	public static void testScheduledThreadPoolExecutor() {
		
		// 适用于需要多个后台线程执行周期任务，同时为了满足资源管理的需求而需要限制后台线程的数量的应用场景
		ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(5);
		
		// 适用于需要单个后台线程执行周期任务，同时需要保证顺序地执行各个任务的应用场景。
		ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
		
	}
	
	public static void testOther() {

		ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
		// Future接口和实现Future接口的FutureTask类用来表示异步计算的结果
		Future<?> submit = newSingleThreadScheduledExecutor.submit(new Runnable() {

			@Override
			public void run() {

			}
		});
		
		/**
		 *  Runnable接口和Callable接口的实现类，都可以被ThreadPoolExecutor或ScheduledThreadPoolExecutor执行。
		 *	它们之间的区别是Runnable不会返回结果，而Callable可以返回结果
		 *
		 */
//		除了可以自己创建实现Callable接口的对象外，还可以使用工厂类Executors来把一个Runnable包装成一个Callable。
//		API --> public static Callable<Object> callable(Runnable task)   FutureTask.get()方法将返回null
//				public static <T> Callable<T> callable(Runnable task, T result)  FutureTask.get()方法将返回result对象
		
		
	}
	
}
