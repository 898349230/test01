package concurr2.ch4.condition2;

public class Test {

	public static void main(String[] args) throws InterruptedException {
		
		/** 使用ReentrantLock唤醒指定线程 */
		MyService service = new MyService();
		ThreadA a = new ThreadA(service);
		ThreadB b = new ThreadB(service);
		
		a.start();
		b.start();
		Thread.sleep(3000);
		// 只通知 conditionA的锁
		service.signalAll_A();
		
	}
}
