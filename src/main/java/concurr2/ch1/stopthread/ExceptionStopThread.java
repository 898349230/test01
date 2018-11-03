package concurr2.ch1.stopthread;

/**
 * 通过异常停止线程
 * @author 
 *
 */
public class ExceptionStopThread extends Thread{

	@Override
	public void run() {
		try {
		for(int i=0; i < 50000; i++) {
			if(interrupted()) {
				System.out.println("已经是停止状态  ，即将退出");
					throw new InterruptedException();
			}
			System.out.println("i= " + (i+1));
		}
		System.out.println("for循环抛出异常  ， 线程并未停止运行， Thread.currentThread().isAlive()= " + Thread.currentThread().isAlive());
		} catch (InterruptedException e) {
			System.out.println( "MyThread2 里面的 run 方法抛出的异常" );
			e.printStackTrace();
		}
	}
	
}
