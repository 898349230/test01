package concurr2.ch3.waitnotiy;

public class Service {

	public void testMethod(Object lock) {
		synchronized (lock) {
			try {
				System.out.println("wait前  ThreadName=" + Thread.currentThread().getName());
				lock.wait();
				System.out.println("wait后  ThreadName=" + Thread.currentThread().getName());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public void synNotifyMethod(Object lock) {
		synchronized(lock){
			try {
				System.out.println("notify前  ThreadName=" + Thread.currentThread().getName());
				lock.notify();
				Thread.sleep(3000);
				System.out.println("notify后  ThreadName=" + Thread.currentThread().getName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	} 
	
}
