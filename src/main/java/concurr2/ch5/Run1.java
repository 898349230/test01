package concurr2.ch5;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Run1 {
	
	private static Timer timer = new Timer();
	// 守护线程
	private static Timer timer2 = new Timer(true);
	
	private static int testCancel2 = 1;
	
	static public class MyTask extends TimerTask{
		@Override
		public void run() {
			try {
				System.out.println("start时间  ： " + new Date());
				Thread.sleep(1000);
				System.out.println("end  时间  ： " + new Date());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	static public class MyTask1 extends TimerTask{
		@Override
		public void run() {
			try {
				System.out.println("start 时间  ： " + new Date());
				Thread.sleep(5000);
				System.out.println("end   时间  ： " + new Date());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	static public class MyTask2 extends TimerTask{
		@Override
		public void run() {
			System.out.println("testCancel2 = " + testCancel2 + " 运行了，时间  ： " + new Date());
		}
	}
	
	static public class MyTask3 extends TimerTask{
		@Override
		public void run() {
			System.out.println("start 时间  ： " + new Date());
			System.out.println("end   时间  ： " + new Date());
		}
	}
	
	static public class TaskA extends TimerTask{
		@Override
		public void run() {
			System.out.println("taskA  运行了，时间  ： " + new Date());
			// TimerTask 的 cancel() 方法只将自身任务从队列中消除
//			this.cancel();
//			Timer 的 cancel() 将任务队列中的全部任务清空
			timer.cancel();
		}
	}
	
	static public class TaskB extends TimerTask{
		@Override
		public void run() {
			System.out.println("taskB  运行了，时间  ： " + new Date());
		}
	}
	
	/**
	 * schedule(TimerTask task, Date firstTime)
	 * 
	 * @throws ParseException
	 */
	public static void test01() throws ParseException {
		MyTask task = new MyTask();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeStr = "2018-01-09 18:35:00";
		Date date = sdf.parse(timeStr);
		System.out.println(date);
		timer.schedule(task, date);
	}
	
	/**
	 * 守护线程 
	 *
	 * @throws ParseException
	 */
	public static void test02() throws ParseException {
		MyTask task = new MyTask();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeStr = "2018-01-09 18:45:00";
		Date date = sdf.parse(timeStr);
		System.out.println(date);
		timer2.schedule(task, date);
	}
	
	/**
	 * 测试不延时
	 * schedule(TimerTask task, Date firstTime, long period) : 每间隔一段时间运行一次<br>
	 * schedule : 下一次任务的开始时间参考的是上一个任务的“开始时间”（上一个任务的开始时间 + period参数）<br>
	 * scheduleAtFixedRate ：下一次任务的开始时间参考的是上一个任务的“结束时间”（个人理解：执行完一个任务后会立即执行下一个任务，period参数没有用） <br>
	 * 	                                                             下一次任务的执行时间是上一个任务的开始时间加上delay的时间
	 * @throws ParseException
	 */
	public static void test03() throws ParseException {
		MyTask task = new MyTask();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeStr = "2018-01-09 18:45:00";
		Date date = sdf.parse(timeStr);
		System.out.println(date);
		// 每隔 2s 运行一次
//		timer2.schedule(task, date, 3000);
		timer2.scheduleAtFixedRate(task, date, 10000);
	}
	
	/**
	 * 测试延时<br>
	 * 
	 * 如果一个线程的任务执行时间过长会导致下一个任务执行时间被延时<br>
	 * schedule 和 scheduleAtFixedRate 方法的下一次任务的执行时间都参考的是上一个任务的“结束时间”（执行完一个任务后会立即执行下一个任务，period参数没有用）<br>
	 * 
	 * @throws ParseException
	 */
	public static void test04() throws ParseException {
		MyTask1 task = new MyTask1();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeStr = "2018-01-09 18:45:00";
		Date date = sdf.parse(timeStr);
		System.out.println(date);
		
		timer2.schedule(task, date, 3000);
//		timer2.scheduleAtFixedRate(task, date, 3000);

	}
	
	/**
	 * TimerTask 的 cancel() 方法只将自身任务从队列中消除
	 * Timer 的 cancel() 将任务队列中的全部任务清空
	 * @throws ParseException
	 */
	public static void testCancel() throws ParseException {
		TaskA taskA = new TaskA();
		TaskB taskB = new TaskB();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeStr = "2018-01-09 18:45:00";
		Date date = sdf.parse(timeStr);
		System.out.println(date);
		// 每隔 3s 运行一次
		timer.schedule(taskA, date, 1000);
		timer.schedule(taskB, date, 1000);
	}
	
	/**
	 * timer.cancel()
	 * task 有可能一直运行，因为cancel()没有争抢到queue锁
	 * 
	 * @throws ParseException
	 */
	public static void testCancel2() throws ParseException {
		while(true) {
			testCancel2++;
			MyTask2 task = new MyTask2();
			Timer timer = new Timer();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String timeStr = "2018-01-09 18:45:00";
			Date date = sdf.parse(timeStr);
			// 每隔 3s 运行一次
			timer.schedule(task, date);
			timer.cancel();
		}
	}
	
	/**
	 *
	 * schedule ：   不具有追赶执行性<br>
	 * scheduleAtFixedRate ： 具有追赶执行性，
	 * 						 两个时间段内对应的task被“补充性”的执行
	 * 
	 * @throws ParseException
	 */
	public static void test05() throws ParseException {
		MyTask3 task = new MyTask3();
		Timer timer = new Timer();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeStr = "2018-01-10 14:27:00";
		Date date = sdf.parse(timeStr);
		System.out.println("timeStr: " + date + " 当前时间：" + new Date());
//		timer.schedule(task, date, 3000);
		
		// scheduleAtFixedRate date 和   当前时间未被执行的任务会被“补充性”的执行
		timer.scheduleAtFixedRate(task, date, 1000 * 10);
	}
	
	public static void main(String[] args) throws ParseException {
//		test01();
//		test02();
//		test03();
//		test04();
//		testCancel();
//		testCancel2();
		
		test05();
	}
	
	
}
