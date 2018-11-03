package concurr2.ch2.syncmethod;

public class ThreadA extends Thread{

	MyObject obj = new MyObject();

	public ThreadA(MyObject obj) {
		this.obj = obj;
	}

	@Override
	public void run() {
		obj.methodA();
	}
	
}
