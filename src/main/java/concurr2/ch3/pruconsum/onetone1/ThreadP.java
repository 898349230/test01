package concurr2.ch3.pruconsum.onetone1;

public class ThreadP extends Thread{

	private P p;
	
	public ThreadP(P p) {
		this.p = p;
	}

	@Override
	public void run() {
		while(true) {
			p.setValue();
		}
	}
}
