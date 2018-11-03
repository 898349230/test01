package concurr2.ch3.waitnotiy;

public class ThreadB extends Thread{

	private Object lock;
	
	public ThreadB(Object lock) {
		this.lock = lock;
	}
	
	@Override
	public void run() {
		
		Service service = new Service();
		
//      测试wait自动释放锁	
//		service.testMethod(lock);
		
//      测试notify不释放锁	
		service.synNotifyMethod(lock);
	
	}
	
}
