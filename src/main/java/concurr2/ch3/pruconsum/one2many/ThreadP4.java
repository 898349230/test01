package concurr2.ch3.pruconsum.one2many;

public class ThreadP4 extends Thread{

	private P4 p;
	
	public ThreadP4(P4 p) {
		this.p = p;
	}

	@Override
	public void run() {
		while(true) {
			p.pushService();
		}
	}
	
}
