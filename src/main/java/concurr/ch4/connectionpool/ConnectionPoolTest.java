package concurr.ch4.connectionpool;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionPoolTest {

	static ConnectionPool pool = new ConnectionPool(10);
	
	/**
	 * 一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。
	 * 用给定的计数 初始化 CountDownLatch。由于调用了 countDown() 方法，所以在当前计数到达零之前，await 方法会一直受阻塞。
	 * 之后，会释放所有等待的线程，await 的所有后续调用都将立即返回。这种现象只出现一次——计数无法被重置。 一个线程(或者多个)， 等待另外N个线程完成某个事情之后才能执行
	 */
	// 保证所有ConnectionRunner能够同时开始
	static CountDownLatch start = new CountDownLatch(1);
	// main线程将会等待所有ConnectionRunner结束后才能继续执行
	static CountDownLatch end;
	
	/** 超时获取 */
	public static void main(String[] args) throws Exception {
		// 线程数量，可以修改线程数量进行观察
		int threadCount = 30;
		end = new CountDownLatch(threadCount);
		int count = 20;
		AtomicInteger got = new AtomicInteger();
		AtomicInteger notGot = new AtomicInteger();
		for (int i = 0; i < threadCount; i++) {
			Thread thread = new Thread(new ConnetionRunner(count, got, notGot), "ConnectionRunnerThread");
			thread.start();
		}
		start.countDown();
		end.await();
		System.out.println("total invoke: " + (threadCount * count));
		System.out.println("got connection: " + got); 
		System.out.println("not got connection " + notGot);
	}

	static class ConnetionRunner implements Runnable {
		
		int count;
		AtomicInteger got;
		AtomicInteger notGot;

		public ConnetionRunner(int count, AtomicInteger got, AtomicInteger notGot) {
			this.count = count;
			this.got = got;
			this.notGot = notGot;
		}

		public void run() {
			try {
				start.await();
			} catch (Exception ex) {
				
			}
			while (count > 0) {
				try {
					// 从线程池中获取连接，如果1000ms内无法获取到，将会返回null
					// 分别统计连接获取的数量got和未获取到的数量notGot
					Connection connection = pool.fetchConnection(1000);
					if (connection != null) {
						try {
							connection.createStatement();
							connection.commit();
						} finally {
							pool.releaseConnection(connection);
							got.incrementAndGet();
						}
					} else {
						notGot.incrementAndGet();
					}
				} catch (Exception ex) {
					
				} finally {
					count--;
				}
			}
			end.countDown();
		}
	}
}
