package concurr2.ch4.reentrantreadwritelock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ServiceRR {

	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	
	public void read() {
		try {
			try {
				lock.readLock().lock();
				System.out.println("Thread " + Thread.currentThread().getName() + " readLock...  time：" + System.currentTimeMillis());
				Thread.sleep(10000);
			}finally {
				lock.readLock().unlock();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
