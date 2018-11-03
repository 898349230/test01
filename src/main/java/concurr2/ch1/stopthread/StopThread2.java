package concurr2.ch1.stopthread;

public class StopThread2 extends Thread{
	
	@Override
	public void run() {
		try {
			// stop 会抛出ThreadDeath异常，但不需要显示获取
			this.stop();
		} catch (ThreadDeath e) {
			System.out.println(" catch 方法内 ");
			e.printStackTrace();
		}
	}

}
