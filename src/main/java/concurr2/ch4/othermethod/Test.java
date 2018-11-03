package concurr2.ch4.othermethod;

public class Test {

	public static void main(String[] args) throws InterruptedException {
		
//		getHoldCount();
//		getQueueLength();
//		getWaitQueueLength();
//		test01();
//		hasWaiters();
//		isLocked();
//		testLock();
//		tryLock();
//		awaitUninterruptibly();
		awaitUntill();
		
	}
	
	/**
	 * getHoldCount()方法<br>
	 * 查询当前线程保持此锁定的个数，及调用lock()方法的次数<br>
	 */
	public static void getHoldCount() {
		Service01 service = new Service01();
		service.serviceMethod01();
	}
	
	/**
	 * getQueueLength()<br>
	 * 返回正在等待获取此锁定的线程的个数<br>
	 * 
	 * @throws InterruptedException 
	 */
	public static void getQueueLength() throws InterruptedException {
		final Service02 service = new Service02();
		
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				service.serviceMethod01();
			}
		};
		
		Thread[] threadArr = new Thread[10];
		for (int i=0; i < 10; i++) {
			threadArr[i] = new Thread(runnable);
		}
		
		for (int i=0; i < 10; i++) {
			threadArr[i].start();
		}
		Thread.sleep(3000);
		System.out.println("当前有 " + service.lock.getQueueLength() + " 个线程等待获取锁");
	}
	
	/**
	 * getWaitQueueLength(Condition condition)<br>
	 * 
	 * 返回正在等待与次锁定相关的给定条件的 conditon的线程的估计数<br>
	 * 
	 * @throws InterruptedException 
	 */
	public static void getWaitQueueLength() throws InterruptedException {
		final Service03 service = new Service03();
		
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				service.waitMethod();
			}
		};
		
		Thread[] threadArr = new Thread[10];
		for (int i=0; i < 10; i++) {
			threadArr[i] = new Thread(runnable);
		}
		
		for (int i=0; i < 10; i++) {
			threadArr[i].start();
		}
		Thread.sleep(3000);
		service.notifyMethod();
	}
	
	/**
	 * hasQueuedThread(Thread thread): 查询指定的线程是否在等待获取此锁定<br>
	 * hasQueuedThreads() ： 查询是否有线程在等待获取当前锁定<br>
	 * 
	 */
	public static void test01() {
		try {
			final Service04 service = new Service04();
			
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					service.waitMethod();
				}
			};
		
			Thread threadA = new Thread(runnable);
			threadA.start();
			Thread.sleep(500);
			Thread threadB = new Thread(runnable);
			threadB.start();
			Thread.sleep(500);
			
			System.out.println("hasQueuedThread(Thread thread) A : " + service.lock.hasQueuedThread(threadA));
			System.out.println("hasQueuedThread(Thread thread) B : " + service.lock.hasQueuedThread(threadB));
			System.out.println("hasQueuedThreads() : " + service.lock.hasQueuedThreads());
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * hasWaiters(Condition condition)： 是否有线程正在等待与此锁定有关的condition条件<br>
	 * 
	 * @throws InterruptedException
	 */
	public static void hasWaiters() throws InterruptedException {
		
		final Service05 service = new Service05();
		
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				service.waitMethod();
			}
		};
		
		Thread[] threadArr = new Thread[10];
		for (int i=0; i < 10; i++) {
			threadArr[i] = new Thread(runnable);
		}
		
		for (int i=0; i < 10; i++) {
			threadArr[i].start();
		}
		Thread.sleep(3000);
		service.notifyMethod();
	}
	
	/**
	 * isFair() : 是否为公平锁， 默认为false<br>
	 * isHeldByCurrentThread() ：当前线程是或否保持此锁定<br>
	 * isLocked() : 此锁定是否由任意线程保持<br>
	 * 
	 * @throws InterruptedException
	 */
	public static void isLocked() throws InterruptedException {
		
		final Service06 service = new Service06();
		
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				service.serviceMethod();
			}
		};
		Thread thread = new Thread(runnable);
		thread.start();
	}
	
	/**
	 * lock.lockInterruptibly() : 如果当前线程未被中断，则获取锁定，如果已经被中断，则抛出异常<br>
	 * lock.lock()则不会抛出异常<br>
	 * 
	 */
	public static void testLock() {
		try {
			final Service07 service = new Service07();
			
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					service.waitMethod();
					;
				}
			};
			
			Thread threadA = new Thread(runnable);
			threadA.setName("A");
			threadA.start();
			Thread.sleep(500);
			Thread threadB = new Thread(runnable);
			threadB.setName("B");
			threadB.start();
			Thread.sleep(500);
			// threadB interrupt
			threadB.interrupt();
			System.out.println("main end...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * tryLock() : 仅在调用时锁定未被另一个线程保持的情况下，才获取该锁定<br>
	 * tryLock(long timeout, TimeUnit unit) : 如果锁定在给定时间内没有被另一个线程保持，
	 * 且当前线程未被中断，则获取该锁定（个人理解为等待一定时间后再次获取锁定）
	 * 
	 */
	public static void tryLock() {
		
		final Service08 service = new Service08();
		
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
//				service.waitMethod01();
				System.out.println("Thread " + Thread.currentThread().getName() + " 调用waitMethod02()时间为  ： " + System.currentTimeMillis());
				service.waitMethod02();
				
			}
		};
		
		Thread threadA = new Thread(runnable);
		threadA.setName("A");
		threadA.start();
		Thread threadB = new Thread(runnable);
		threadB.setName("B");
		threadB.start();
	}
	
	/**
	 * awaitUninterruptibly() : 当前thread如果被interrupt 不会抛出异常<br>
	 * awai() : 当前thread如果被interrupt 会抛出异常
	 */
	public static void awaitUninterruptibly(){
		
		try {
			Service09 service = new Service09();
			Thread01 thread = new Thread01(service);
			thread.start();
			Thread.sleep(2000);
			// 如果是 awaitUninterruptibly() 不会抛出异常
			// 如果是 awai() interrupt会抛出异常
			thread.interrupt();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * awaitUntil(Date deadline) ：一个await的线程在 deadline 之前可以被其他线程提前唤醒
	 *  
	 */
	public static void awaitUntill() {
		
		Service09 service = new Service09();
		
		ThreadA ta = new ThreadA(service);
		ta.setName("A");
		ta.start();
		
		ThreadB tb = new ThreadB(service);
		tb.setName("B");
		tb.start();
	}
	
}
