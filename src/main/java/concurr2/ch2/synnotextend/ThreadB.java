package concurr2.ch2.synnotextend;

public class ThreadB extends Thread{

	private Sub sub ;
	
	public ThreadB(Sub sub) {
		this.sub = sub;
	}

	@Override
	public void run() {
		sub.serviceMethod();
	}
	
}
