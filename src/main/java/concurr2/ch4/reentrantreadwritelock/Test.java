package concurr2.ch4.reentrantreadwritelock;

public class Test {

	public static void main(String[] args) {
//		readRead();
		
//		writeWrite();
		
		readWrite();
		
	}
	
	/**
	 * 读读之间共享，不互斥
	 * 两个线程的 readLock 几乎同时进入
	 */
	public static void readRead() {
		ServiceRR service = new ServiceRR();
		ThreadRR threadA = new ThreadRR(service);
		threadA.setName("A");
		
		ThreadRR threadB = new ThreadRR(service);
		threadB.setName("B");

		threadA.start();
		threadB.start();
	}
	
	
	/**
	 * 写写之间互斥
	 * 两个线程的 writeLock 互相排斥
	 */
	public static void writeWrite() {
		ServiceWW service = new ServiceWW();
		ThreadWW threadA = new ThreadWW(service);
		threadA.setName("A");
		
		ThreadWW threadB = new ThreadWW(service);
		threadB.setName("B");
		
		threadA.start();
		threadB.start();
	}
	
	/**
	 * 读写之间互斥
	 * 写读也互斥
	 * 两个线程的 readLock 和 writeLock 互相排斥
	 */
	public static void readWrite() {
		ServiceRW service = new ServiceRW();
		ThreadRWA threadA = new ThreadRWA(service);
		threadA.setName("A");
		
		ThreadRWB threadB = new ThreadRWB(service);
		threadB.setName("B");
		
		threadA.start();
		threadB.start();
	}
	
	
	
}
