package concurr2.ch4.fairlock;

public class Test {

	public static void main(String[] args) {
		
		// 结果线程的运行顺序和获得锁的顺序一致
//		testFairLock();
		
		// 结果线程的运行顺序和获得锁的顺序不一致，运行结果基本是乱序的，说明start()启动的线程不一定先获得锁
		testNotFairLock();
		
	}
	
	/**
	 * 测试公平锁
	 * 
	 * 公平锁表示线程获得锁的顺序是按照加锁的顺序来分配的 符合FIFO先进先出
	 * 非公平锁是随机获取锁
	 * 
	 */
	public static void testFairLock() {
		
		final MyService service = new MyService(true);
		
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				System.out.println("线程  " + Thread.currentThread().getName() + " 运行");
				service.serviceMethod();
			}
		};
		
		Thread[] threadArr = new Thread[10];
		for (int i=0; i < 10; i++) {
			threadArr[i] = new Thread(runnable);
		}
		
		for (int i=0; i < 10; i++) {
			threadArr[i].start();
		}
		
	}
	
	/**
	 * 测试非公平锁
	 */
	public static void testNotFairLock() {
		final MyService service = new MyService(false);
		
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				System.out.println("线程  " + Thread.currentThread().getName() + " 运行");
				service.serviceMethod();
			}
		};
		
		Thread[] threadArr = new Thread[10];
		for (int i=0; i < 10; i++) {
			threadArr[i] = new Thread(runnable);
		}
		
		for (int i=0; i < 10; i++) {
			threadArr[i].start();
		}
		
	}
	
}
