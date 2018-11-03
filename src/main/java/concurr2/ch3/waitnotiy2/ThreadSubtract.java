package concurr2.ch3.waitnotiy2;

public class ThreadSubtract extends Thread{

	private String lock;
	
	public ThreadSubtract(String lock) {
		this.lock = lock;
	}
	
	@Override
	public void run() {
		Subtract subtract = new Subtract(lock);
		subtract.subtract();
	}
	
}
