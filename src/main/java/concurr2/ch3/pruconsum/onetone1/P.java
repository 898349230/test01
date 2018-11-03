package concurr2.ch3.pruconsum.onetone1;

import concurr2.ch3.pruconsum.ValueObject;

public class P {

	private String lock;
	
	public P(String lock) {
		this.lock = lock;
	}
	
	public void setValue() {
		try {
			synchronized (lock) {
				if(ValueObject.value.equals("")) {
					ValueObject.value = "A";
					System.out.println("生产    " + "A" + "  ThreadName=" + Thread.currentThread().getName());
					lock.notify();
				}
				Thread.sleep(1000);
				lock.wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
