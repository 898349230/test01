package concurr2.ch5;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Delay {

	private static Timer timer = new Timer();
	
	static public class MyTask extends TimerTask{
		@Override
		public void run() {
			System.out.println("运行时间  ： " + new Date());
		}
	}
	
	static public class MyTask1 extends TimerTask{
			@Override
			public void run() {
				try {
					System.out.println("start 时间  ： " + new Date());
						Thread.sleep(1000);
					System.out.println("end   时间  ： " + new Date());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
	
	public static void main(String[] args) {
		delay01();
		
	}
	
	/**
	 * schedule(TimerTask task, long delay)：
	 * 延时 delay时间运行<br>
	 * schedule(TimerTask task, long delay, long period)
	 * 延时 delay时间运行后再以period的间隔一直运行<br>
	 * 
	 */
	public static void delay01() {
		MyTask task = new MyTask();
		System.out.println("当前时间  ： " + new Date());
//		timer.schedule(task, 3000);
		timer.schedule(task, 3000, 2000);
	}
	
}
