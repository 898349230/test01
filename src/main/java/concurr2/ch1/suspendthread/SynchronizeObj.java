package concurr2.ch1.suspendthread;

public class SynchronizeObj {

	synchronized public void print() {
		
		System.out.println("begin ");
		if(Thread.currentThread().getName().equals("A")) {
			System.out.println("A线程 suspend中。。。。 ");
			Thread.currentThread().suspend();
		}
		System.out.println("end ");
		
	}
}
