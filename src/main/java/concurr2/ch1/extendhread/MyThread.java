package concurr2.ch1.extendhread;

public class MyThread extends Thread {

	@Override
	public void run() {
		try {
			for (int i = 0; i < 10; i++) {
				int time = (int) (Math.random() * 1000);
				Thread.sleep(time);
				System.out.println("run = " + Thread.currentThread().getName() + " i= " + i);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
