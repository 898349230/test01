package concurr2.ch4.othermethod;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Service05 {

	private ReentrantLock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	public void waitMethod() {
		try {
			lock.lock();
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
			System.out.println("是否有线程在等待 condition " + lock.hasWaiters(condition) + 
					"  等待线程数为：  " + lock.getWaitQueueLength(condition));
			condition.signal();
		}finally {
			lock.unlock();
		}
	}
	
}
