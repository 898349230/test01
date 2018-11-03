package concurr2.ch3.waitnotiy2;

public class Subtract {
	
	private String lock;
	
	public Subtract(String lock) {
		this.lock = lock;
	}
	
	public void subtract() {
		try {
			synchronized(lock) {
//				
				// 有可能报异常  因为两个wait线程被同时notify后再都进行 remove 
//				操作有可能在第二个线程remove 的时候 list已经空了，被第一个线程remove了
				if(ValueObject.list.size() == 0) {
					System.out.println("wait前  ThreadName=" + Thread.currentThread().getName());
					lock.wait();
					System.out.println("wait后  ThreadName=" + Thread.currentThread().getName());
				}
			
				// 不会报错
//				while(ValueObject.list.size() == 0) {
//					System.out.println("wait前  ThreadName=" + Thread.currentThread().getName());
//					lock.wait();
//					System.out.println("wait后  ThreadName=" + Thread.currentThread().getName());
//				}
				
				ValueObject.list.remove(0);
				System.out.println("list size = " + ValueObject.list.size() + " threadName = " + Thread.currentThread().getName());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
