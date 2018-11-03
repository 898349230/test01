package concurr2.ch4.reentrantreadwritelock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ServiceRW {

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
