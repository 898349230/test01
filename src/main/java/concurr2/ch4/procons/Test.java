package concurr2.ch4.procons;

public class Test {

	public static void main(String[] args) {
//		test01();
		
		test02();

	}
	
	/**
	 * 单生产，单消费
	 */
	public static void test01() {
		MyService service = new MyService();
		
		ProductThread pt = new ProductThread(service);
		ConsumerThread ct = new ConsumerThread(service);
		
		pt.start();
		ct.start();
	}
	
	/**
	 * 多生产，多消费
	 */
	public static void test02() {
		
		MyService service = new MyService();
		
		ProductThread[] pt = new ProductThread[10];
		ConsumerThread[] ct = new ConsumerThread[10];
		
		for(int i=0; i<10; i++){
			pt[i] = new ProductThread(service);
			ct[i] = new ConsumerThread(service);
			pt[i].start();
			ct[i].start();
		}
		
	}
	
}
