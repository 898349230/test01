package concurr2.ch2.syncmethod;

public class Test {

	public static void main(String[] args) {
		
//		test01();
//		test02();
		test03();
	}
	
	// 出现异常，锁会自动释放
	
	/**
	 * 测试同步的方法
	 *  synchronized 获取的是对象的锁！！
	 * A线程先持有obj的锁，B线程可以异步调用obj的非synchronized的方法
	 * A线程先持有obj的锁，如果B线程调用obj的synchronized的方法需要等待
	 *  
	 */
	public static void test01() {
		
		MyObject obj = new MyObject();
		
		ThreadA threadA = new ThreadA(obj);
		threadA.setName("A");
		
		ThreadB threadB = new ThreadB(obj);
		threadB.setName("B");

		threadA.start();
		threadB.start();
		
	}
	
	/**
	 * 测试重入锁
	 * 当一个线程获取到一个对象的锁时，再次请求该对象的锁时是可以获得该对象的锁的
	 * 也就是说当一个synchronized方法/块内调用其他synchronized方法/块的时候，是永远可以得到锁的
	 * 
	 * 自己可以再次获取自己的内部锁
	 */
	public static void test02() {
		
		MyThread1 t1 = new MyThread1();
		t1.setName("A");
		t1.start();
		
	} 
	
	/**
	 * 测试重入锁(可继承)
	 * 当一个线程获取到一个对象的锁时，再次请求该对象的锁时是可以获得该对象的锁的
	 * 也就是说当一个synchronized方法/块内调用其他synchronized方法/块的时候，是永远可以得到锁的
	 * 
	 * 自己可以再次获取自己的内部锁
	 */
	public static void test03() {
		
		MyThread2 t2 = new MyThread2();
		t2.setName("A");
		t2.start();
		
	} 
	
	
}
