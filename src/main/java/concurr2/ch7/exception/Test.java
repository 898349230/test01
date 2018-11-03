package concurr2.ch7.exception;

import java.lang.Thread.UncaughtExceptionHandler;

public class Test {

	public static void main(String[] args) {
		
//		test01();
		
		test02();
	}
	
	/**
	 * setUncaughtExceptionHandler()
	 * 对指定线程对象设置默认的异常处理器
	 * 
	 */
	public static void test01() {
		MyThread t1 = new MyThread();
		t1.setName("A");
		t1.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println("线程 " + t.getName() + " 发生异常");
				e.printStackTrace();
			}
		});
		
		MyThread t2 = new MyThread();
		t2.setName("B");
		
		t1.start();
		t2.start();
	}
	
	/**
	 * setDefaultUncaughtExceptionHandler()
	 * 对所有线程对象设置默认的异常处理器
	 * 
	 */
	public static void test02() {
		
		MyThread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println("线程 " + t.getName() + " 发生异常");
				e.printStackTrace();
			}
		});
		
		MyThread t1 = new MyThread();
		t1.setName("A");
		
		MyThread t2 = new MyThread();
		t2.setName("B");
		
		t1.start();
		t2.start();
	}
	
	
}
