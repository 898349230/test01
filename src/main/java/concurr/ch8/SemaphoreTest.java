package concurr.ch8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
	
	private static final int THREAD_COUNT = 30;
	private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);
	// Semaphore（10）表示允许10个线程获取许可证，也就是最大并发数是10
	private static Semaphore semaphore = new Semaphore(10);

	/**
	 * Semaphore可以用于做流量控制
	 * 虽然有30个线程在执行，但是只允许10个并发执行
	 * 
	 *  ·int availablePermits()：返回此信号量中当前可用的许可证数。
	 *	·int getQueueLength()：返回正在等待获取许可证的线程数。
	 *	·boolean hasQueuedThreads()：是否有线程正在等待获取许可证。
	 *	·void reducePermits（int reduction）：减少reduction个许可证，是个protected方法。
	 *	·Collection getQueuedThreads()：返回所有等待获取许可证的线程集合，是个protected方法
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		for (int i = 0; i < THREAD_COUNT; i++) {
			threadPool.execute(new Runnable() {
				@Override
				public void run() {
					try {
//						acquire()方法获取一个许可证, 也可用tryAcquire()方法尝试获取许可证
						semaphore.acquire();
						System.out.println("save data  " + "availablePermits = " + semaphore.availablePermits() + "  getQueueLength = " + semaphore.getQueueLength() + "  hasQueuedThreads = " + semaphore.hasQueuedThreads());
//						release()方法归还许可证
						semaphore.release();
					} catch (InterruptedException e) {
					}
				}
			});
		}
		threadPool.shutdown();
	}
}
