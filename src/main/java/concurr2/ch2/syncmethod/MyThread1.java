package concurr2.ch2.syncmethod;

public class MyThread1 extends Thread{

	@Override
	public void run() {
		Service service = new Service();
		service.service1();
	}
	
}
