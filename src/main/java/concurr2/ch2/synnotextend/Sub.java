package concurr2.ch2.synnotextend;

public class Sub extends Main {

	@Override
	synchronized
	public void serviceMethod() {
		try {
			System.out.println("sub begin threadName= "+ Thread.currentThread().getName() + "  time=" + System.currentTimeMillis());
			Thread.sleep(5000);
			System.out.println("sub end threadName= "+ Thread.currentThread().getName() + "  time=" + System.currentTimeMillis());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	
}
