package concurr2.ch4.procons;

/**
 * 生产线程
 * @author 
 *
 */
public class ConsumerThread extends Thread{

	private MyService service;
	
	public ConsumerThread(MyService service) {
		this.service = service;
	}
	
	@Override
	public void run() {
		while(true) {
			service.setValue();
		}
	}
	
}
