package concurr2.ch3.join;

public class ThreadA extends Thread{

	ThreadB b ;
	
	public ThreadA(ThreadB b) {
		this.b = b;
	}

	@Override
	public void run() {
		synchronized(b) {
			try {
				System.out.println("ThreadA begin...");
				Thread.sleep(5000);
				System.out.println("ThreadA end...");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
}
