package concurr2.ch2.synstatic;

public class Service2 {

//	static
	public void printA() {
		
		synchronized(Service2.class){
			try {
				System.out.println("printA 同步代码块 begin threadName=" + Thread.currentThread().getName());
				Thread.sleep(3000);
				System.out.println("printA 同步代码块 end threadName=" + Thread.currentThread().getName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
//	static 
	public void printB() {

		synchronized(Service2.class) {
			System.out.println("printB 同步代码块 begin threadName=" + Thread.currentThread().getName());
			System.out.println("printB 同步代码块 end threadName=" + Thread.currentThread().getName());
		}
		
	}
	
}
