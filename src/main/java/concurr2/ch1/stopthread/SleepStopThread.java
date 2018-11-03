package concurr2.ch1.stopthread;

/**
 * 在sleep()中停止线程
 * @author
 *
 */
public class SleepStopThread extends Thread{

	@Override
	public void run() {
		try {
			System.out.println("run begin ");
			Thread.sleep(20000);
			System.out.println("run end ");
		} catch (InterruptedException e) {
			System.out.println("先sleep， 后interrupt ， InterruptedException异常中 " + this.isInterrupted());
			e.printStackTrace();
		}
	}

	
}
