package concurr.ch4;

import java.util.concurrent.TimeUnit;

import concurr.ch5.SleepUtils;

/**
 * 在JVM退出时daemon线程的finally内的代码不一定执行，不能依靠finally块中的内容来确保执行关闭或清理资源的逻辑。
 * @author Administrator
 *
 */
public class Daemon {
	public static void main(String[] args) throws InterruptedException {
		
		Thread thread = new Thread(new DaemonRunner(), "DaemonRunner");
		thread.setDaemon(true);
		thread.start();
		TimeUnit.SECONDS.sleep(5);
	}

	static class DaemonRunner implements Runnable {
		@Override
		public void run() {
			try {
				System.out.println("run...");
				SleepUtils.second(10);
			} finally {
				System.out.println("DaemonThread finally run.");
			}
		}
	}
}