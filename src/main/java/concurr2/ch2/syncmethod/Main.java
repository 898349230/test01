package concurr2.ch2.syncmethod;

public class Main {

	public int i = 10;
	
	synchronized public void operateIMain() {
		try {
				System.out.println("main: i= " + i);
				i--;
				Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
