package concurr2.ch4.reentrantreadwritelock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ServiceWW {

	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	
	public void write() {
		try {
			try {
				lock.writeLock().lock();
				System.out.println("Thread " + Thread.currentThread().getName() + " writeLock...  time：" + System.currentTimeMillis());
				Thread.sleep(10000);
			}finally {
				lock.writeLock().unlock();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
