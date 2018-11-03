package concurr2.ch4.othermethod;

public class ThreadB extends Thread{
	
	private Service09 service;
	
	public ThreadB(Service09 service) {
		this.service = service;
	}
	
	@Override
	public void run() {
		service.notifyMethod();
	}
}
