package concurr2.ch2.synblock;

public class Test {

	public static void main(String[] args) {
		
//		test01();
		test02();
	}
	
	/**
	 *  锁非this同步块的好处：如果一个对象中有多个synchronize方法，虽然会同步，但是会阻塞，影响效率
	 *  
	 *  如果使用同步代码块锁住非this对象，则synchronize(非this)代码块与 同步的方法是异步的，不与其他
	 *  锁this同步方法争抢this锁，提高效率
	 *  
	 */
	public static void test01() {
		// 使用synchronized(非this的其他对象) 代码块进行同步时，对象监视器必须是同一个对象，否则会异步调用
		Service service = new Service();
		ThreadA ta = new ThreadA(service);
		ta.setName("A");

		ThreadB tb = new ThreadB(service);
		tb.setName("B");
		
		ta.start();
		tb.start();
	}
	
	/**
	 * synchronized(非this的其他对象x)代码块进行同步与
	 * synchronized同步的方法会异步调用，不会阻塞
	 * 
	 * 其他线程执行x对象内的synchronized同步方法，或者synchronized(this)同步块的时候也会呈现同步效果
	 * 
	 */
	public static void test02() {
		// 使用synchronized(非this的其他对象) 代码块进行同步时，对象监视器必须是同一个对象，否则会异步调用
		Service2 service = new Service2();
		ThreadA2 ta = new ThreadA2(service);
		ta.setName("A");
		
		ThreadB2 tb = new ThreadB2(service);
		tb.setName("B");
		
		ta.start();
		tb.start();
	}
	
	
}
