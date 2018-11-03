package concurr2.ch2.dirtyread;

public class MyThread extends Thread{
	
	PublicVar var ;

	public MyThread(PublicVar var) {
		this.var = var;
	}

	@Override
	public void run() {
		var.setValue("B", "BB");
	}
	
}
