package concurr2.ch4.reentrantreadwritelock;

public class ThreadWW extends Thread{
	private ServiceWW service;
	
	public ThreadWW(ServiceWW service) {
		this.service = service;
	}
	
	@Override
	public void run() {
		service.write();
	}
	
}
