package concurr2.ch2.synnotextend;

public class ThreadA extends Thread{

	private Sub sub ;
	
	public ThreadA(Sub sub) {
		this.sub = sub;
	}

	@Override
	public void run() {
		sub.serviceMethod();
	}
	
}
