package concurr2.ch1.stopthread;

public class Test {

	public static void main(String[] args) {
//		test01();
//		test02();
//		test03();
		test04();
//		test05();
		
	}
	
	/**
	 * break 不会停止线程
	 */
	public static void test01() {
		try {
			MyThread1 thread = new MyThread1();
			thread.start();
			Thread.sleep(100);
			thread.interrupt();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("end  ");
	}
	
	/**
	 * 通过异常停止线程
	 */
	public static void test02() {
		try {
			ExceptionStopThread thread = new ExceptionStopThread();
			thread.start();
			Thread.sleep(100);
			thread.interrupt();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("end  ");
	}
	
	/**
	 * 在sleep中停止线程
	 * 先 sleep， 再interrupt
	 * 
	 */
	public static void test03() {
		try {
			SleepStopThread thread = new SleepStopThread();
			thread.start();
			Thread.sleep(1000);
			thread.interrupt();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("main end  ");  // 会输出
	}
	
	/**
	 * 在sleep中停止线程
	 * 先 interrupt， 再sleep
	 * 
	 */
	public static void test04() {
		SleepStopThread2 thread = new SleepStopThread2();
		thread.start();
		thread.interrupt();
		System.out.println("main end  "); // 不会输出
	}
	
	/**
	 * 暴力停止
	 * 通过stop方法停止线程
	 * 会造成数据不一致的问题，已弃用
	 */
	public static void test05() {
		try {
			StopThread thread = new StopThread();
			thread.start();
			Thread.sleep(2000);
			thread.stop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("main end  "); 
	}
	
}
