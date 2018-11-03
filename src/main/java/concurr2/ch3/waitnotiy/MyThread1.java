package concurr2.ch3.waitnotiy;

public class MyThread1 extends Thread{

	private String lock ;
	
	public MyThread1(String lock) {
		this.lock = lock;
	}
	
	@Override
	public void run() {
		try {
			synchronized (lock) {
				System.out.println("wait前  ThreadName=" + Thread.currentThread().getName());
				lock.wait();
				System.out.println("wait后  ThreadName=" + Thread.currentThread().getName());
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
