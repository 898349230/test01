package concurr2.ch1.interrupt;

public class Test {

	public static void main(String[] args) {
//		test1();
		test2();
//		test3();
		
	}
	
	/**
	 * 测试  interrupted()
	 * 测试当前线程是否是停止状态
	 */
	public static void test1() {
		MyThread t = new MyThread();
		try {
			t.start();
			Thread.sleep(10);
			// interrupt() 并不会停止线程
			t.interrupt(); 
			// interrupted() 判断当前线程是否停止，（这里是main线程）
			System.out.println("是否停止1?" + t.interrupted()); // false
			System.out.println("是否停止2?" + t.interrupted()); // false
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("end ");
	}
	
	public static void test2() {
		Thread.currentThread().interrupt();
		System.out.println(" main 线程没有停止！！！ ");
		System.out.println("1:  Thread.currentThread().getName(): "+ Thread.currentThread().getName() + 
				"\rThread.interrupted(): " + Thread.interrupted());
		// 第二次调用 interrupted() 方法会有清除状态的作用，返回 false
		System.out.println("2:  Thread.currentThread().getName(): "+ Thread.currentThread().getName() + 
				"\rThread.interrupted(): " + Thread.interrupted());
	}
	
	/**
	 * 测试  isInterrupted()
	 * 测试线程对象是否是停止状态
	 */
	public static void test3() {
		try {
			MyThread t = new MyThread();
			t.start();
			Thread.sleep(10);
			// interrupt() 并不会停止线程
			t.interrupt(); 
			System.out.println("是否停止1?" + t.isInterrupted());  
			System.out.println("是否停止2?" + t.isInterrupted()); 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("end ");
	}
	
}
