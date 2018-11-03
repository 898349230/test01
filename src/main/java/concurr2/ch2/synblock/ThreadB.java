package concurr2.ch2.synblock;

public class ThreadB extends Thread{

	Service service ;

	public ThreadB(Service service) {
		this.service = service;
	}

	@Override
	public void run() {
		service.setValue("B", "BB");
	}
	
}
