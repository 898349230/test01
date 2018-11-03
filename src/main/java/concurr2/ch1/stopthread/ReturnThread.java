package concurr2.ch1.stopthread;

/**
 * 使用 return 停止线程
 * @author 
 *
 */
public class ReturnThread extends Thread{

	@Override
	public void run() {
		while(true) {
			if(this.isInterrupted()) {
				System.out.println("停止了。。。。");
				return ;
			}
			System.out.println("time= " + System.currentTimeMillis());
		}
	}

}
