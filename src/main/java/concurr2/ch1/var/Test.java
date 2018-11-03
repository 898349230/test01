package concurr2.ch1.var;

public class Test {
	
	public static void main(String[] args) {
		// 测试变量不共享
//		test01();
		// 测试变量共享
//		test02();
		
		testLogin();
		
	}
	
	/**
	 * 测试变量不共享
	 */
	public static void test01() {
		MyThread01 t01 = new MyThread01("t01");
		MyThread01 t02 = new MyThread01("t02");
		MyThread01 t03 = new MyThread01("t03");
		t01.start();
		t02.start();
		t03.start();
	}
	
	/**
	 * 测试变量共享
	 */
	public static void test02() {
		MyThread02 thread = new MyThread02();
		Thread t1 = new Thread(thread, "A");
		Thread t2 = new Thread(thread, "B");
		Thread t3 = new Thread(thread, "C");
		Thread t4 = new Thread(thread, "D");
		Thread t5 = new Thread(thread, "E");
		Thread t6 = new Thread(thread, "F");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
	}
	
	
	public static void testLogin() {
		
		DoLoginA la = new DoLoginA();
		la.start();
		DoLoginB lb = new DoLoginB();
		lb.start();
		
	}
	
}
