package concurr2.ch1.yieldthread;

public class MyThread extends Thread{

	@Override
	public void run() {
		long begin = System.currentTimeMillis();
		long count = 0;
		for(int i=0; i<5000000; i++) {
			// yield 会让当前线程放弃当前cpu的时间片，重新获取时间片
			Thread.yield();
			count += i;
		}
		long end = System.currentTimeMillis();
		System.out.println("用时： " + (end - begin));
	}
	
}
