package concurr2.ch4.othermethod;

import java.util.concurrent.locks.ReentrantLock;

public class Service07 {

	public ReentrantLock lock = new ReentrantLock();

	public void waitMethod() {
		try {
//			lock.lock();
			lock.lockInterruptibly();
			System.out.println("Thread " + Thread.currentThread().getName() + " start... ");
			for(int i = 0; i< Integer.MAX_VALUE / 10 ; i++) {
				Math.random();
			}
			System.out.println("Thread " + Thread.currentThread().getName() + " end... ");
		} catch (InterruptedException e) {
			System.out.println("异常了 ：  InterruptedException.....");
			e.printStackTrace();
		} finally {
			if(lock.isHeldByCurrentThread()) {
				lock.unlock();
			}
		}
	}
	
}
