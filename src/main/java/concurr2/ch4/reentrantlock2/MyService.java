package concurr2.ch4.reentrantlock2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyService {

	private Lock lock = new ReentrantLock();
	
	public void methodA() {
		
		try {
			lock.lock();
			System.out.println("thread " + Thread.currentThread().getName() + "  methodA  start");
			Thread.sleep(2000);
			System.out.println("thread " + Thread.currentThread().getName() + "  methodA  end");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
	public void methodB() {
		try {
			lock.lock();
			System.out.println("thread " + Thread.currentThread().getName() + "  methodB  start");
			Thread.sleep(2000);
			System.out.println("thread " + Thread.currentThread().getName() + "  methodB  end");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
}
