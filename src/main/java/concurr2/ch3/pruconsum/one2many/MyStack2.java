package concurr2.ch3.pruconsum.one2many;

import java.util.ArrayList;
import java.util.List;

public class MyStack2 {

	private List<String> list = new ArrayList<String>();
	
	synchronized public void push() {
		try {
			while(list.size() == 1) {
				System.out.println("push waitting ThreadName=" + Thread.currentThread().getName());
				this.wait();
			}
			System.out.println("push running 生产 ThreadName=" + Thread.currentThread().getName());
			list.add("something");
//			this.notify();
			// 避免假死
			this.notifyAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	synchronized public void pop() {
		try {
			while(list.size() == 0) {
				System.out.println("pop waitting ThreadName=" + Thread.currentThread().getName());
				this.wait();
			}
			System.out.println("pop running 消费 ThreadName=" + Thread.currentThread().getName());
			list.remove(0);
//			Thread.sleep(3000);
//			this.notify();
			// 避免假死
			this.notifyAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
