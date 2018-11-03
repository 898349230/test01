package concurr2.ch2.syncmethod;

public class ThreadB extends Thread{

	MyObject obj = new MyObject();

	public ThreadB(MyObject obj) {
		this.obj = obj;
	}

	@Override
	public void run() {
		obj.methodB();
	}
	
}
