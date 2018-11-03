package concurr2.ch1.stopthread;

public class StopThread extends Thread{
	
	@Override
	public void run() {
		try {
			int i = 0;
			while(true) {
				System.out.println("i= " + ((i++)+1));
				Thread.sleep(300);
			}
		} catch (InterruptedException e) {
			System.out.println("InterruptedException异常中 " + this.isInterrupted());
			e.printStackTrace();
		}
	}

}
