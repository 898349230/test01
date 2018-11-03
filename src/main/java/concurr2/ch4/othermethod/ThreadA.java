package concurr2.ch4.othermethod;

public class ThreadA extends Thread{
	
	private Service09 service;
	
	public ThreadA(Service09 service) {
		this.service = service;
	}
	
	@Override
	public void run() {
		service.waitMethod();
	}
}
