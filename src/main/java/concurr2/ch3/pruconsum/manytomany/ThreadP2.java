package concurr2.ch3.pruconsum.manytomany;

public class ThreadP2 extends Thread{

	private P2 p;
	
	public ThreadP2(P2 p) {
		this.p = p;
	}

	@Override
	public void run() {
		while(true) {
			p.setValue();
		}
	}
	
	
}
