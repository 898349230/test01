package concurr2.ch1.var;

/**
 * 
 * 不共享变量
 *
 */
public class MyThread01 extends Thread{
	private int count = 5;
	public MyThread01(String name) {
		this.setName(name);
	}

	@Override
	public void run() {
		for (int i=0; i < 5 ; i++) {
			System.out.println(Thread.currentThread().getName() + " " + count--);
		}
	}

}
