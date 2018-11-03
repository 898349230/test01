package concurr2.ch4.othermethod;

import java.util.concurrent.locks.ReentrantLock;

public class Service01 {

	private ReentrantLock lock = new ReentrantLock();
	
	public void serviceMethod01() {
		try {
			lock.lock();
			System.out.println("serviceMethod01 : lock.getHoldCount() : " + lock.getHoldCount());
			this.serviceMethod02();
		}finally {
			lock.unlock();
		}
	}
	
	public void serviceMethod02() {
		try {
			lock.lock();
			System.out.println("serviceMethod02 : lock.getHoldCount() : " + lock.getHoldCount());
		}finally {
			lock.unlock();
		}
	}
	
}
