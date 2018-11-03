package concurr2.ch4.procons;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyService {

	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	private boolean hasValue = false; 
	
	/**
	 * 生产者
	 */
	public void setValue() {
		try {
			lock.lock();
			while(!hasValue) {
				System.out.println(" 生产中。。。 ");
				this.hasValue = true;
//				Thread.sleep(2000);
				condition.signalAll();
			}
			condition.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		
	}
	
	/**
	 * 消费者
	 */
	public void getValue() {
		try {
			lock.lock();
			
			while(hasValue) {
				System.out.println(" 消费中。。。");
				this.hasValue = false;
//				Thread.sleep(2000);
				condition.signalAll();
			}
			condition.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
		
	}
	
	
}
