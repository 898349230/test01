package concurr2.ch2.synstatic;

public class ThreadB extends Thread{

	private Service service ;
	
	public ThreadB(Service service) {
		this.service = service;
	}

	@Override
	public void run() {
		service.printB();
	}
	
}
