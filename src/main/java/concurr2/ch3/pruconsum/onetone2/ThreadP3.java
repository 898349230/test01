package concurr2.ch3.pruconsum.onetone2;

public class ThreadP3 extends Thread{

	private P3 p;
	
	public ThreadP3(P3 p) {
		this.p = p;
	}

	@Override
	public void run() {
		while(true) {
			p.pushService();
		}
	}
	
}
