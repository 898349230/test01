package concurr2.ch4.fairlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyService {

	private Lock lock;
	
	public MyService(boolean isFair) {
		lock = new ReentrantLock(isFair);
	}
	
	public void serviceMethod() {
		try {
			lock.lock();
			System.out.println("Thread " + Thread.currentThread().getName() + " 获得锁");
		}finally{
			lock.unlock();
		}
	}
	
}
