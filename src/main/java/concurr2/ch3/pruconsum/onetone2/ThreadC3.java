package concurr2.ch3.pruconsum.onetone2;

public class ThreadC3 extends Thread{

	private C3 c;
	
	public ThreadC3(C3 c) {
		this.c = c;
	}

	@Override
	public void run() {
		while(true) {
			c.popService();
		}
	}
}
