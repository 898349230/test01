package concurr2.ch4.othermethod;

import java.util.concurrent.locks.ReentrantLock;

public class Service06 {

	public ReentrantLock lock = new ReentrantLock();

	public void serviceMethod() {
		try {
			System.out.println("lock.isFair() : " + lock.isFair());
			System.out.println("lock.lock()前：  --> lock.isHeldByCurrentThread() " + lock.isHeldByCurrentThread());
			System.out.println("lock.lock()前：  --> lock.isLocked() " + lock.isLocked());
			lock.lock();
			System.out.println("lock.lock()后：  --> lock.isHeldByCurrentThread() " + lock.isHeldByCurrentThread());
			System.out.println("lock.lock()后：  --> lock.isLocked() " + lock.isLocked());
			Thread.sleep(Integer.MAX_VALUE);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
}
