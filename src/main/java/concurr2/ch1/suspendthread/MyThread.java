package concurr2.ch1.suspendthread;

public class MyThread extends Thread{

	private int i=0;

	@Override
	public void run() {
		while(true) {
			i++;
			// println 方法是同步的，如果 该线程被 suspend，会一直持有锁 
			System.out.println(i);
		}
	}
	
}
