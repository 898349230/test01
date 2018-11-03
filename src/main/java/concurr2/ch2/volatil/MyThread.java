package concurr2.ch2.volatil;

public class MyThread extends Thread{

//	volatile 
	public static int count;
	
	
	synchronized   //  addCount 方法未加 synchronized 时不同步
	public static void addCount() {
		for(int i=0; i<100; i++) {
			count++;
			System.out.println(count);
		}
	}
	
	@Override
	public void run() {
		addCount();
	}
	
}
