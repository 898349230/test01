package concurr2.ch3.waitnotiy2;

public class ThreadAdd extends Thread{

	private String lock;
	
	public ThreadAdd(String lock) {
		this.lock = lock;
	}
	
	@Override
	public void run() {
		Add add = new Add(lock);
		add.add();
	}
	
}
