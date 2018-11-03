package concurr2.ch3.pruconsum.manytomany;

import concurr2.ch3.pruconsum.ValueObject;

public class P2 {

	private String lock;
	
	public P2(String lock) {
		this.lock = lock;
	}
	
	public void setValue() {
		try {
			synchronized (lock) {
				
				while(!ValueObject.value.equals("")) {
					System.out.println("生产   waitting  ThreadName=" + Thread.currentThread().getName());
					lock.wait();
				}
				
				System.out.println("生产   running  ThreadName=" + Thread.currentThread().getName());
				ValueObject.value = "产品A";
//				lock.notify();
//				 notifyAll()方法，唤醒所有wait的线程，为避免假死
				lock.notifyAll();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
