package concurr2.ch2.syncmethod;

public class MyObject {

	synchronized public void methodA() {
		try {
			System.out.println("methodA : threadName: " + Thread.currentThread().getName());
			Thread.sleep(3000);
			System.out.println("methodA : threadName: " + Thread.currentThread().getName() +"  endTime: " + System.currentTimeMillis());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	synchronized
	public void methodB() {
		try {
			System.out.println("methodB : threadName: " + Thread.currentThread().getName() +"  startTime: " + System.currentTimeMillis());
			Thread.sleep(3000);
			System.out.println("methodB end : threadName: " + Thread.currentThread().getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
