package concurr2.ch2.synnotextend;

public class Main {
	
	synchronized public void serviceMethod() {
		try {
			System.out.println("main begin threadName= "+ Thread.currentThread().getName() + "  time=" + System.currentTimeMillis());
			Thread.sleep(5000);
			System.out.println("main end threadName= "+ Thread.currentThread().getName() + "  time=" + System.currentTimeMillis());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
