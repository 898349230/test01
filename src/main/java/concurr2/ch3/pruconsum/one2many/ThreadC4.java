package concurr2.ch3.pruconsum.one2many;

public class ThreadC4 extends Thread{

	private C4 c;
	
	public ThreadC4(C4 c) {
		this.c = c;
	}

	@Override
	public void run() {
		while(true) {
			c.popService();
		}
	}
}
