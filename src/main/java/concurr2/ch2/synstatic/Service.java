package concurr2.ch2.synstatic;

public class Service {

	synchronized public static void printA() {
		try {
			System.out.println("printA 同步代码块 begin threadName=" + Thread.currentThread().getName());
			Thread.sleep(3000);
			System.out.println("printA 同步代码块 end threadName=" + Thread.currentThread().getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	synchronized public static void printB() {
		System.out.println("printB 同步代码块 begin threadName=" + Thread.currentThread().getName());
		System.out.println("printB 同步代码块 end threadName=" + Thread.currentThread().getName());
	}
	
}
