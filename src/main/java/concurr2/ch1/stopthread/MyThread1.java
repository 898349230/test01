package concurr2.ch1.stopthread;

public class MyThread1 extends Thread{

	@Override
	public void run() {
		for(int i=0; i < 50000; i++) {
			if(interrupted()) {
				System.out.println("已经是停止状态  ，即将退出");
				break;
			}
			System.out.println("i= " + (i+1));
		}
		System.out.println("for 循环 break 后  ， 线程并未停止运行， Thread.currentThread().isAlive()= " + Thread.currentThread().isAlive());
	}
	
}
