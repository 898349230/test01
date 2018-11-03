package concurr.ch8.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

	/**
	 * 参数表示屏障拦截的线程数量，每个线程调用await方法告诉CyclicBarrier我已经到达了屏障，然后当前线程被阻塞 
	 * 如果 变量 c1 的构造方法 CyclicBarrier(2)改为CyclicBarrier(3)，则test1方法中主线程和子线程会永远等待，因为没有第三个线程执行await方法，
	 * 即没有第三个线程到达屏障， 所以之前到达屏障的两个线程都不会继续执行 
	 * 
	 */
	static CyclicBarrier c1 = new CyclicBarrier(2);
	/**
	 * CyclicBarrier（int parties，Runnable barrierAction），用于在线程到达屏障时，优先执行barrierAction，方便处理更复杂的业务场景
	 */
	static CyclicBarrier c2 = new CyclicBarrier(2, new A());

	/**
	 * 让一组线程到达一个屏障（也可以叫同步点）时被阻塞，直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续运行
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

//		test1();
		test2();
	}
	
	/**
	 * 
	 */
	public static void test1() {
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					c1.await();
				} catch (Exception e) {
				}
				System.out.println(1);
			}
		}).start();
		
		try {
			c1.await();
		} catch (Exception e) {
		}
		
		System.out.println(2);
	}
	/**
	 * 
	 */
	public static void test2() {
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
//					Thread.sleep(100);
					c2.await();
				} catch (Exception e) {
				}
				System.out.println(1);
			}
		}).start();
		
		try {
			
			System.out.println("getNumberWaiting = " + c2.getNumberWaiting());
//			重新设置 计数，这个test中如果c2.getNumberWaiting()=1表明已经有一个线程执行行了await()方法，
//			这时重置计数会导致main线程，子线程一直等待
			c2.reset();
			c2.await();
		} catch (Exception e) {
		}
		
		System.out.println(2);
	}
	
	static class A implements Runnable {
		@Override
		public void run() {
			System.out.println(3);
		}
	}
	
}
