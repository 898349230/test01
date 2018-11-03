package concurr2.ch2.synblock;

public class Service2 {

	private String anything = new String();
	
	public void a() {
		try {
//			synchronized (this) {
			synchronized (anything) {
				System.out.println("a 同步代码块 begin threadName=" + Thread.currentThread().getName());
				Thread.sleep(3000);
				System.out.println("a 同步代码块 end threadName=" + Thread.currentThread().getName());
			}
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	synchronized public void b() {
		try {
			System.out.println("b 同步方法 begin threadName=" + Thread.currentThread().getName());
			Thread.sleep(3000);
			System.out.println("b 同步方法 end threadName=" + Thread.currentThread().getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
