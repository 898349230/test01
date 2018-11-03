package concurr2.ch2.syncmethod;

public class Service {

	synchronized public void service1() {
		try {
			System.out.println( "threadName "+ Thread.currentThread().getName() + "  service1。。。。  ");
			Thread.sleep(2000);
			service2();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	synchronized public void service2() {
		try {
			System.out.println( "threadName "+ Thread.currentThread().getName() + "  service2。。。。  ");
			Thread.sleep(2000);
			service3();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	synchronized public void service3() {
		try {
			System.out.println( "threadName "+ Thread.currentThread().getName() + "  service3。。。。  ");
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	} 
	
}
