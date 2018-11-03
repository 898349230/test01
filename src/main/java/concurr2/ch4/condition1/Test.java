package concurr2.ch4.condition1;

public class Test {

	public static void main(String[] args) throws InterruptedException {
		
		/** lock 的 await，signal 方法实现 等待/通知模式  */
		/** 
		 * await() --> wait()
		 * await(long time, TimeUnit unit) --> wait(long time)
		 * singal() --> notify()
		 * signalAll --> notifyAll()
		 */
		
		MyService service = new MyService();
		ThreadA t = new ThreadA(service);
		t.start();
		Thread.sleep(3000);
		service.signal();
		
	}
}
