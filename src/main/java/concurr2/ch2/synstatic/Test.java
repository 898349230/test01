package concurr2.ch2.synstatic;

public class Test {

	public static void main(String[] args) {
//		test01();
		test02();
	}
	
	/**
	 * 测试
	 * synchronized加在static方法上
	 * 是class锁，对类的每个实例对象都起作用
	 * 
	 */
	public static void test01() {
		
		Service service1 = new Service();
		Service service2 = new Service();
		
		ThreadA ta = new ThreadA(service1);
		ta.setName("A");
		ThreadB tb = new ThreadB(service2);
		tb.setName("B");
		
		ta.start();
		tb.start();
	}
	
	/**
	 * 测试
	 * synchronized(class){}
	 * 与synchronized加在static方法上作用一样
	 * 是class锁，对类的每个实例对象都起作用
	 * 
	 */
	public static void test02() {
		
		Service2 service1 = new Service2();
		Service2 service2 = new Service2();
		
		ThreadA2 ta = new ThreadA2(service1);
		ta.setName("A");
		ThreadB2 tb = new ThreadB2(service2);
		tb.setName("B");
		
		ta.start();
		tb.start();
	}
	
}
