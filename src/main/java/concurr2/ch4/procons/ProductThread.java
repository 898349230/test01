package concurr2.ch4.procons;

/**
 * 生产线程
 * @author 
 *
 */
public class ProductThread extends Thread{

	private MyService service;
	
	public ProductThread(MyService service) {
		this.service = service;
	}
	
	@Override
	public void run() {
		while(true) {
			service.getValue();
		}
	}
	
}
