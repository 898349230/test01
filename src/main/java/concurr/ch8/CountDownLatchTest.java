package concurr.ch8;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

	public static void main(String[] args) throws InterruptedException {

//		 testJoin();
		
		/**
		 * CountDownLatch的构造函数接收一个int类型的参数作为计数器，如果你想等待N个点完成，这里就传入N。计数器只能使用一次
		 * 
		 * 当我们调用CountDownLatch的countDown方法时，N就会减1，CountDownLatch的await方法会阻塞当前线程，直到N变成零。
		 * 由于countDown方法可以用在任何地方，所以这里说的N个点，可以是N个线程，也可以是1个线程里的N个执行步骤。用在多个线程时，
		 * 只需要把这个CountDownLatch的引用传递到线程里即可。
		 * 
		 */
		testCountDownLatch();
		
	}
	
	static CountDownLatch countDownLatch = new CountDownLatch(2);

	/**
	 * 使用CountDownLatch
	 * {@link package concurr.ch4.countdownlatch }
	 * @throws InterruptedException
	 */
	public static void testCountDownLatch() throws InterruptedException {
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(1);
				countDownLatch.countDown();
				System.out.println(2);
				countDownLatch.countDown();
				try {
//					当前线程 sleep 100ms，countDownLatch.await()方法所在的线程中的 “3”会优先于“AAAA”打印
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("AAAA");
			}
		}).start();

		countDownLatch.await();
		System.out.println("3");
	}
	
	public static void testJoin() throws InterruptedException {

		Thread parser1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("parser1 finish");
			}
		});

		Thread parser2 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("parser2 finish");
			}
		});
		parser1.start();
		parser2.start();
		parser1.join();
		parser2.join();
		System.out.println("all parser finish");
	}

}
