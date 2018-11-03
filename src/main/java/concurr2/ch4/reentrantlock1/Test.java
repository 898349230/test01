package concurr2.ch4.reentrantlock1;

public class Test {

	public static void main(String[] args) {
		
		test01();
		
	}
	
	/**
	 * 测试reentrantlock
	 */
	public static void test01() {
		
		MyService service = new MyService();
		
		MyThread t1 = new MyThread(service);
		MyThread t2 = new MyThread(service);
		MyThread t3 = new MyThread(service);
		MyThread t4 = new MyThread(service);
		MyThread t5 = new MyThread(service);
		
		t2.start();
		t3.start();
		t1.start();
		t4.start();
		t5.start();
		
	}
}
