package concurr2.ch2.synblock;

public class ThreadA2 extends Thread{

	Service2 service ;

	public ThreadA2(Service2 service) {
		this.service = service;
	}

	@Override
	public void run() {
		service.a();
	}
	
}
