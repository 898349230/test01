package concurr.ch5.twinslock;

import java.util.concurrent.locks.Lock;

import concurr.ch5.SleepUtils;

/**
 * 测试该工具在同一时刻，只允许至多两个线程同时访问，超过两个线程的访问将被阻塞
 * @author 
 *
 */
public class Test {

	public static void main(String[] args) {

		final Lock lock = new TwinsLock();
		class Worker extends Thread {
			public void run() {
				while (true) {
					lock.lock();
					try {
						SleepUtils.second(1);
						System.out.println(Thread.currentThread().getName());
						SleepUtils.second(1);
					} finally {
						lock.unlock();
					}
				}
			}
		}
		// 启动10个线程
		for (int i = 0; i < 10; i++) {
			Worker w = new Worker();
			w.setDaemon(true);
			w.start();
		}
		// 每隔1秒换行
		for (int i = 0; i < 10; i++) {
			SleepUtils.second(1);
			System.out.println();
		}

	}
}
