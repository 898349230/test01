package concurr2.ch4.reentrantreadwritelock;

public class ThreadRWA extends Thread{
	private ServiceRW service;
	
	public ThreadRWA(ServiceRW service) {
		this.service = service;
	}
	
	@Override
	public void run() {
		service.read();;
	}
	
}
