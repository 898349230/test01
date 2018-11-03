package concurr2.ch4.condition2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyService {

	private Lock lock = new ReentrantLock();
	private Condition conditionA = lock.newCondition();
	private Condition conditionB = lock.newCondition();
	
	public void awaitA() {
		try {
			lock.lock();
			System.out.println("awaitA 时间： " + System.currentTimeMillis());
			conditionA.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
	public void awaitB() {
		try {
			lock.lock();
			System.out.println("awaitB 时间： " + System.currentTimeMillis());
			conditionB.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
	public void signalAll_A() {
		try {
			lock.lock();
			System.out.println("signalAll_A 时间： " + System.currentTimeMillis());
			conditionA.signalAll();
		}finally {
			lock.unlock();
		}
	}
	
	public void signalAll_B() {
		try {
			lock.lock();
			System.out.println("signalAll_B 时间： " + System.currentTimeMillis());
			conditionB.signalAll();
		}finally {
			lock.unlock();
		}
	}
}
