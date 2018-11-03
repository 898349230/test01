package concurr2.ch4.reentrantreadwritelock;

public class ThreadRWB extends Thread{
	private ServiceRW service;
	
	public ThreadRWB(ServiceRW service) {
		this.service = service;
	}
	
	@Override
	public void run() {
		service.write();
	}
	
}
