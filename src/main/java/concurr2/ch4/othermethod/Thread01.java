package concurr2.ch4.othermethod;

public class Thread01 extends Thread{
	
	private Service09 service;
	
	public Thread01(Service09 service) {
		this.service = service;
	}
	
	@Override
	public void run() {
		service.testMethod();
	}
}
