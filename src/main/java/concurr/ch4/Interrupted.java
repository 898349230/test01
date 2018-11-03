package concurr.ch4;

import java.util.concurrent.TimeUnit;

public class Interrupted {
	
	/**
	 * 许多声明抛出InterruptedException的方法（例如Thread.sleep(longmillis)方法）
	 * 这些方法在抛出InterruptedException之前，Java虚拟机会先将该线程的中断标识位
	 * 清除，然后抛出InterruptedException，此时调用isInterrupted()方法将会返回false。
	 *
	 * 如果该线程已经处于终结状态，即使该线程被中断过，在调用该线程对象的isInterrupted()时依旧会返回false
	 *
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// sleepThread不停的尝试睡眠
		Thread sleepThread = new Thread(new SleepRunner(), "SleepThread");
		sleepThread.setDaemon(true);
		// busyThread不停的运行
		Thread busyThread = new Thread(new BusyRunner(), "BusyThread");
		busyThread.setDaemon(true);
		sleepThread.start();
		busyThread.start();
		// 休眠5秒，让sleepThread和busyThread充分运行
		TimeUnit.SECONDS.sleep(5);
		sleepThread.interrupt();
		busyThread.interrupt();
//		sleepThread 其中断标识位被清除了
		System.out.println("SleepThread interrupted is " + sleepThread.isInterrupted());
		System.out.println("BusyThread interrupted is " + busyThread.isInterrupted());
		// 防止sleepThread和busyThread立刻退出SleepUtils.second(2);
	}

	static class SleepRunner implements Runnable {
		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
				}
			}
		}
	}

	static class BusyRunner implements Runnable {
		@Override
		public void run() {
			while (true) {
			}
		}
	}
}