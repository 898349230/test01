package concurr2.ch3.waitnotiy;

public class Test {

	public static void main(String[] args) {
		
//		test01();
		test02();
		
		// wait 中的线程 如果被 interrupt 会抛出异常
	}
	
	/**
	 * 测试 wait 和  notify
	 * 
	 * notify 用于唤醒 处于wait 状态的线程
	 * 要等到 notify这个当前线程退出 synchronized 代码块后 才会释放当前锁
	 * 
	 * notify 只是随机唤醒一个线程
	 * notifyAll 唤醒所有线程
	 */
	public static void test01() {
	
		try {
			String lock = "lock";
			MyThread1 t1 = new MyThread1(lock);
			MyThread2 t2 = new MyThread2(lock);
			t1.start();
			Thread.sleep(3000);
			t2.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * wait 会自动释放锁
	 * notify 锁不会自动释放，需要等到当前代码执行完才释放锁
	 * 
	 */
	public static void test02() {
		
		Object lock = new Object();
		ThreadA ta = new ThreadA(lock);
		ThreadB tb = new ThreadB(lock);
		
		ta.start();
		tb.start();
	}
	
	
	
}
