package concurr.ch9;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

	public static void main(String[] args) {
		
		long maxMemory = Runtime.getRuntime().maxMemory();
		// cpu个数
		int cpu = Runtime.getRuntime().availableProcessors();
		System.out.println(maxMemory + " " + cpu);
		
		doTask();
	}
	
	/**
	 * 线程池的监控
	 */
	public static void monitorThreadPool() {
		
		ThreadPoolExecutor executor = 
				new ThreadPoolExecutor(10, 20, 100, TimeUnit.HOURS, new LinkedBlockingQueue<Runnable>());

		// 线程池需要执行的任务数量
		long taskCount = executor.getTaskCount();
		// 线程池在运行过程中已完成的任务数量，小于或等于taskCount。
		long completedTaskCount = executor.getCompletedTaskCount();
		// 线程池里曾经创建过的最大线程数量。
		int largestPoolSize = executor.getLargestPoolSize();
		// 线程池的线程数量。如果线程池不销毁的话，线程池里的线程不会自动销毁，所以这个大小只增不减。
		int poolSize = executor.getPoolSize();
		// 获取活动的线程数。
		int activeCount = executor.getActiveCount();
	}
	
	/**
	 * 继承线程池来自定义线程池，重写线程池的beforeExecute、afterExecute和terminated方法，
	 * 也可以在任务执行前、执行后和线程池关闭前执行一些代码来进行监控
	 * 
	 * @author  
	 */
	static class MyThreadPool extends ThreadPoolExecutor{

		public MyThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
				BlockingQueue<Runnable> workQueue) {
			super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		}
		
		public void beforeExecute() {
			
		}
		
		public void afterExecute() {
			
		}
		
		public void terminated() {
			
		}
		
	}
	
	/**
	 * 向线程池提交任务
	 */
	public static void doTask() {
		
		Executor executor = Executors.newFixedThreadPool(4);
		Executor ex = Executors.newSingleThreadExecutor();
		/**
		 * execute()方法用于提交不需要返回值的任务，所以无法判断任务是否被线程池执行成功。
		 *  
		 */
		executor.execute(new Runnable() {
			@Override
			public void run() {
				
			}
		});
		
		/**
		 * submit()方法用于提交需要返回值的任务。线程池会返回一个future类型的对象, 通过这个future对象可以判断任务是否执行成功，
		 * 并且可以通过future的get()方法来获取返回值，get()方法会阻塞当前线程直到任务完成，
		 * 而使用get（long timeout，TimeUnit unit）方法则会阻塞当前线程一段时间后立即返回，这时候有可能任务没有执行完。
		 */
		ThreadPoolExecutor executor2 = 
				new ThreadPoolExecutor(10, 20, 100, TimeUnit.HOURS, new LinkedBlockingQueue<Runnable>());
		Future<?> submit = executor2.submit(new Runnable() {
			@Override
			public void run() {
				
			}
		});
		
		Future<?> submit2 = executor2.submit(new Callable<String>() {

			@Override
			public String call() throws Exception {
				return "done";
			}
			
		});
		
		try {
			Object object = submit.get();
			Object object2 = submit2.get();
			System.out.println(object + "  " + object2);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}finally {
			
			/**
			 * shutdown()和shutdownNow()原理都是遍历线程池中的工作线程，然后逐个调用线程的interrupt方法来中断线程，所以无法响应中断的任务可能永远无法终止
			 * 
			 * 只要调用了这两个关闭方法中的任意一个，isShutdown方法就会返回true。当所有的任务都已关闭后，才表示线程池关闭成功，
			 * 这时调用isTerminaed方法会返回true
			 * 
			 */
			
			/**
			 * shutdown只是将线程池的状态设置成SHUTDOWN状态，然后中断所有没有正在执行任务的线程
			 */
			executor2.shutdown();
			/**
			 * shutdownNow首先将线程池的状态设置成STOP，然后尝试停止所有的正在执行或暂停任务的线程，并返回等待执行任务的列表
			 * 
			 */
			List<Runnable> waitList = executor2.shutdownNow();
		}
	}
	
	/**
	 * 构造方法各个参数
	 * 
	 */
	public static void constructor() {
		
		/**
		 * 1) corePoolSize（线程池的基本大小）：当提交一个任务到线程池时，线程池会创建一个线程来执行任务，即使其他空闲的基本线程能够执行新任务也会创建线程，
		 * 等到需要执行的任务数大于线程池基本大小时就不再创建。如果调用了线程池的prestartAllCoreThreads()方法，线程池会提前创建并启动所有基本线程。
		 * 
		 * 2) workQueue（任务队列）：用于保存等待执行的任务的阻塞队列。可以选择以下几个阻塞队列
		 * 		1:ArrayBlockingQueue：是一个基于数组结构的有界阻塞队列，此队列按FIFO（先进先出）原则对元素进行排序。
		 * 		2:LinkedBlockingQueue：一个基于链表结构的阻塞队列，此队列按FIFO排序元素，吞吐量通常要高于ArrayBlockingQueue。
		 * 			静态工厂方法Executors.newFixedThreadPool()使用了这个队列。
		 * 		3:SynchronousQueue：一个不存储元素的阻塞队列。每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态，
		 * 			吞吐量通常要高于Linked-BlockingQueue，静态工厂方法Executors.newCachedThreadPool使用了这个队列。
		 * 		4:PriorityBlockingQueue：一个具有优先级的无限阻塞队列
		 * 
		 * 3)maximumPoolSize（线程池最大数量）：线程池允许创建的最大线程数。如果队列满了，并且已创建的线程数小于最大线程数，则线程池会再创建新的线程执行任务。
		 * 	值得注意的是，如果使用了无界的任务队列这个参数就没什么效果
		 * 
		 * 4)threadFactory : 用于设置创建线程的工厂，可以通过线程工厂给每个创建出来的线程设置更有意义的名字。使用开源框架guava提供的ThreadFactoryBuilder可以快速给线程池里的线程设置有意义的名字，代码如下
		 * 		new ThreadFactoryBuilder().setNameFormat("XX-task-%d").build();
		 * 
		 * 5)handler， RejectedExecutionHandler（饱和策略）：当队列和线程池都满了，说明线程池处于饱和状态，那么必须采取一种策略处理提交的新任务。
		 * 		这个策略默认情况下是AbortPolicy，表示无法处理新任务时抛出异常。在JDK 1.5中Java线程池框架提供了以下4种策略。
		 * 		1：AbortPolicy：直接抛出异常。
		 * 		2：CallerRunsPolicy：只用调用者所在线程来运行任务。
		 * 		3：DiscardOldestPolicy：丢弃队列里最近的一个任务，并执行当前任务
		 * 		4：DiscardPolicy：不处理，丢弃掉。
		 * 	也可以根据应用场景需要来实现RejectedExecutionHandler接口自定义策略。如记录日志或持久化存储不能处理的任务。
		 * 
		 * 6)keepAliveTime（线程活动保持时间）：线程池的工作线程空闲后，保持存活的时间。所以，如果任务很多，并且每个任务执行的时间比较短，可以调大时间，提高线程的利用率。
		 * 
		 * 7)TimeUnit（线程活动保持时间的单位）：可选的单位有天（DAYS）、小时（HOURS）、分钟（MINUTES）、毫秒（MILLISECONDS）、微秒（MICROSECONDS，千分之一毫秒）和纳秒（NANOSECONDS，千分之一微秒）。
		 */
//		ThreadPoolExecutor pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, 
//				unit, workQueue, threadFactory, handler)
		
	}
	
	
}
