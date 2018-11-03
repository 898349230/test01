package concurr2.ch3.threadlocal;

public class Test {

	public static ThreadLocal<String> tl = new ThreadLocal<String>();
	
	public static void main(String[] args) {
		
//		test01();
		
//		test02();
	
//		test03();

		test04();
	}
	
	public static void test01() {
		if(tl.get() == null){
			System.out.println("threadLocal 的值 为空");
			tl.set("threadLocal 的值");
		}
		System.out.println(tl.get());
		System.out.println(tl.get());
	}
	
	/**
	 * 测试ThreadLocal设置初始值，与隔离性
	 * 
	 * 子线程和父线程有各自的值
	 * 
	 */
	public static void test02() {
		
		try {
			for(int i=0; i < 10; i++) {
				System.out.println("main中：  " + Tools.tl.get());
				Thread.sleep(100);
			}
			Thread.sleep(5000);
			ThreadA a = new ThreadA();
			a.start();
		} catch (InterruptedException e) {
		
		}
	}
	
	/**
	 * 使用InheritableThreadLocal可以让子线程从父线程哪里获取值
	 * 
	 * 注意：如果子线程在取得值的同时，主线程将InheritableThreadLocal的值进行修改，那么子线程取得的还是旧值
	 * 
	 */
	public static void test03() {
		
		try {
			for(int i = 0; i<10 ; i++) {
				System.out.println("main..." + Tools2.tl.get());
			}
			Thread.sleep(5000);
			ThreadB b = new ThreadB();
			b.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 使用InheritableThreadLocal可以让子线程从父线程哪里获取值
	 * 并且对该值进行进一步处理
	 * 
	 * 注意：如果子线程在取得值的同时，主线程将InheritableThreadLocal的值进行修改，那么子线程取得的还是旧值
	 * 
	 */
	public static void test04() {
		
		try {
			for(int i = 0; i<10 ; i++) {
				System.out.println("main..." + Tools3.tl.get());
			}
			Thread.sleep(5000);
			ThreadC c = new ThreadC();
			c.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
