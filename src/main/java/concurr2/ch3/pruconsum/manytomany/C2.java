package concurr2.ch3.pruconsum.manytomany;

import concurr2.ch3.pruconsum.ValueObject;

public class C2 {

	private String lock;
	
	public C2(String lock) {
		this.lock = lock;
	}

	public void getValue() {
		synchronized (lock) {
			try {
				while(ValueObject.value.equals("")) {
					System.out.println("消费   waitting  ThreadName=" + Thread.currentThread().getName());
					lock.wait();
				}
				System.out.println("消费    running  ThreadName=" + Thread.currentThread().getName());
				ValueObject.value = "";
//				lock.notify();
//				 notifyAll()方法，唤醒所有wait的线程
				lock.notifyAll();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
