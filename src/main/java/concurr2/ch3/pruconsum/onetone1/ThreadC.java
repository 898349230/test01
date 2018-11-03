package concurr2.ch3.pruconsum.onetone1;

public class ThreadC extends Thread{

	private C c;
	
	public ThreadC(C c) {
		this.c = c;
	}

	@Override
	public void run() {
		while(true) {
			c.getValue();
		}
	}
}
