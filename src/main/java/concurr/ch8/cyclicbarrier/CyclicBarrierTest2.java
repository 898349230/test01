package concurr.ch8.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest2 {

	static CyclicBarrier c = new CyclicBarrier(2);

	
	/**
	 * isBroken() : 用来了解阻塞的线程是否被中断
	 * reset() : 重置计数器
	 * getNumberWaiting() : 方法可以获得Cyclic-Barrier阻塞的线程数量
	 * 
	 * @param args
	 * @throws InterruptedException
	 * @throws BrokenBarrierException
	 */
	public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
		
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					c.await();
				} catch (Exception e) {
				}
			}
		});
		
		thread.start();
//		Thread.sleep(1000);
//		System.out.println("getNumberWaiting  :  " + c.getNumberWaiting());
		thread.interrupt();
		
		try {
			c.await();
		} catch (Exception e) {
			System.out.println(c.isBroken());
		}
	}
	
}
