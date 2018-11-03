package concurr2.ch3.pruconsum.onetone1;

import concurr2.ch3.pruconsum.ValueObject;

public class C {

	private String lock;
	
	public C(String lock) {
		this.lock = lock;
	}

	public void getValue() {
		synchronized (lock) {
			try {
				if(ValueObject.value.equals("")) {
					lock.wait();
				}
				System.out.println("消费了 " + ValueObject.value + " ThreadName=" + Thread.currentThread().getName());
				ValueObject.value = "";
				lock.notify();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
