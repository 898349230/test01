package concurr2.ch1.stopthread;

/**
 * 在sleep()中停止线程
 * @author
 *
 */
public class SleepStopThread2 extends Thread{

	@Override
	public void run() {
		try {
			for(int i=0; i<2000; i++) {
				System.out.println("i= " + (i+1));
			}
			System.out.println("run begin ");
			this.sleep(20000);
			System.out.println("run end ");
		} catch (InterruptedException e) {
			System.out.println("先interrupt，后sleep ， InterruptedException异常中 " + this.isInterrupted());
			e.printStackTrace();
		}
	}

	
}
