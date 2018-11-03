package concurr2.ch2.syncmethod;

public class Sub extends Main {

	synchronized public void operateISub() {
		try {
			while(i > 0) {
				System.out.println("sub: i= " + i);
				i--;
				Thread.sleep(10);
				this.operateIMain();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
