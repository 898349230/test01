package concurr2.ch2.synblock;

public class ThreadB2 extends Thread{

	Service2 service ;

	public ThreadB2(Service2 service) {
		this.service = service;
	}

	@Override
	public void run() {
		service.b();
	}
	
}
