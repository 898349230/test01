package concurr2.ch2.synstring;

public class ThreadA extends Thread{

	private Service service ;
	
	public ThreadA(Service service) {
		this.service = service;
	}

	@Override
	public void run() {
		
		service.print("AA");
//		service.print2(new Object());
		
	}
	
}
