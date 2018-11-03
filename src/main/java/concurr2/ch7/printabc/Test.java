package concurr2.ch7.printabc;

public class Test {

	public static void main(String[] args) {
		Object lock = new Object();
		
		MyThread ta = new MyThread(lock, "A", 1);
		MyThread tb = new MyThread(lock, "B", 2);
		MyThread tc = new MyThread(lock, "C", 0);
		
		ta.start();
		tb.start();
		tc.start();
		
	}
}
