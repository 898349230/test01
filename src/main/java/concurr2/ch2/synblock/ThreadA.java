package concurr2.ch2.synblock;

public class ThreadA extends Thread{

	Service service ;

	public ThreadA(Service service) {
		this.service = service;
	}

	@Override
	public void run() {
		service.setValue("A", "AA");
	}
	
}
