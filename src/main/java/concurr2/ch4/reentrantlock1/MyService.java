package concurr2.ch4.reentrantlock1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyService {

	private Lock lock = new ReentrantLock();
	
	public void serviceMethod() {
		lock.lock();
		for(int i = 0 ; i < 5; i++) {
			System.out.println("thread " + Thread.currentThread().getName() + "  " + (i+1));
			
		}
		lock.unlock();
	}
	
}
