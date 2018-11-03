package concurr2.ch3.threadlocal;

public class ThreadB extends Thread{

	@Override
	public void run() {
		try {
			for(int i = 0; i < 10; i++) {
				System.out.println("ThreadB： " + Tools2.tl.get());
				Thread.sleep(10);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

