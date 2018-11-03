package concurr2.ch3.waitnotiy2;


public class Add {

	private String lock;
	
	public Add(String lock) {
		this.lock = lock;
	}
	
	public void add() {
		synchronized(lock) {
			ValueObject.list.add("anything");
			lock.notifyAll();
		}
	}
}
