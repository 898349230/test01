package concurr2.ch4.reentrantreadwritelock;

public class ThreadRR extends Thread{
	private ServiceRR service;
	
	public ThreadRR(ServiceRR service) {
		this.service = service;
	}
	
	@Override
	public void run() {
		service.read();
	}
	
}
