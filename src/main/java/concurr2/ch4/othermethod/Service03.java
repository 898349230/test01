package concurr2.ch4.othermethod;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Service03 {

	private ReentrantLock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	public void waitMethod() {
		try {
			lock.lock();
			System.out.println("Thread " + Thread.currentThread().getName() + "waitMethod....");
			condition.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
	public void notifyMethod() {
		try {
			lock.lock();
			System.out.println("有 " + lock.getWaitQueueLength(condition) + " 个线程在等待 condition ");
			condition.signal();
		}finally {
			lock.unlock();
		}
	}
	
}
