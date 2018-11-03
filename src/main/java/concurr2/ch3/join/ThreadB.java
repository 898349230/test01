package concurr2.ch3.join;

public class ThreadB extends Thread{

	public ThreadB() {
		
	}

	@Override
	synchronized public void run() {
		try {
			System.out.println("ThreadB begin...");
			Thread.sleep(5000);
			System.out.println("ThreadB end...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
