package concurr2.ch2.synobjectchange;

public class ThreadB extends Thread{

	private MyService service ;
	
	public ThreadB(MyService service) {
		this.service = service;
	}

	@Override
	public void run() {
		
		service.methodA();
		
	}
	
}
