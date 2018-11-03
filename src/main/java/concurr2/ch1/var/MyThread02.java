package concurr2.ch1.var;

public class MyThread02 extends Thread{
	private int count = 5;
	@Override
	synchronized public void run() {
		count--;
		System.out.println(Thread.currentThread().getName() + " " + count);
	}

}
