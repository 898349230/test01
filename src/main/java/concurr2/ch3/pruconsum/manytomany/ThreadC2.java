package concurr2.ch3.pruconsum.manytomany;

public class ThreadC2 extends Thread{

	private C2 c;
	
	public ThreadC2(C2 c) {
		this.c = c;
	}

	@Override
	public void run() {
		while(true) {
			c.getValue();
		}
	}
}
