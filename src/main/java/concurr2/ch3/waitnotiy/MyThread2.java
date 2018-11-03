package concurr2.ch3.waitnotiy;

public class MyThread2 extends Thread{

	private String lock ;
	
	public MyThread2(String lock) {
		this.lock = lock;
	}
	
	@Override
	public void run() {
		synchronized (lock) {
			System.out.println("notify前  ThreadName=" + Thread.currentThread().getName());
			lock.notify();
			System.out.println("notify后  ThreadName=" + Thread.currentThread().getName());
		}
	}
}
