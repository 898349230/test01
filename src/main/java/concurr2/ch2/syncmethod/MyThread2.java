package concurr2.ch2.syncmethod;

public class MyThread2 extends Thread{

	@Override
	public void run() {
		Sub sub = new Sub();
		sub.operateISub();
	}
	
}
