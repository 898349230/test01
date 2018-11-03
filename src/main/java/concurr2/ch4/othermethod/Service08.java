package concurr2.ch4.othermethod;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Service08 {

	public ReentrantLock lock = new ReentrantLock();

	public void waitMethod01() {
		
		if(lock.tryLock()) {
			System.out.println("Thread " + Thread.currentThread().getName() + " 获得了锁");
		}else {
			System.out.println("Thread " + Thread.currentThread().getName() + " 没有获得锁");
		}
		
	}
	
	public void waitMethod02() {
		
		try {
			if(lock.tryLock(3, TimeUnit.SECONDS)) {
				System.out.println("Thread " + Thread.currentThread().getName() + " 获得了锁， 获得锁的时间为  ： " + System.currentTimeMillis());
				Thread.sleep(10000);
			}else {
				System.out.println("Thread " + Thread.currentThread().getName() + " 没有获得锁， 未获得锁时间为 ：" + System.currentTimeMillis());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}
