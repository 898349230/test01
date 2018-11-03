package concurr2.ch4.othermethod;

import java.util.Calendar;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Service09 {

	private ReentrantLock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	public void waitMethod() {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.SECOND, 10);
			lock.lock();
			System.out.println(Thread.currentThread().getName() + " wait begin " + System.currentTimeMillis());
			condition.awaitUntil(calendar.getTime());
			System.out.println(Thread.currentThread().getName() + " wait end " + System.currentTimeMillis());
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
	public void notifyMethod() {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.SECOND, 10);
			lock.lock();
			System.out.println(Thread.currentThread().getName() + " notify begin " + System.currentTimeMillis());
			condition.signalAll();
			System.out.println(Thread.currentThread().getName() + " notify end " + System.currentTimeMillis());
		}finally {
			lock.unlock();
		}
	}
	
	public void testMethod() {
		try {
			lock.lock();
			System.out.println("wait begin。。。");
			condition.awaitUninterruptibly();
//			condition.await();
			System.out.println("wait end。。。");
		}
//		catch (InterruptedException e) {
//			System.out.println("awit()  抛出异常。。。。。。InterruptedException。。。。");
//			e.printStackTrace();
//		} 
		finally {
			lock.unlock();
		}
	}
	
}
