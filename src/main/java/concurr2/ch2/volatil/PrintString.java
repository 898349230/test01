package concurr2.ch2.volatil;

public class PrintString extends Thread{

	private boolean isContinuePrint = true;
	
	public boolean isContinuePrint() {
		return isContinuePrint;
	}
	
	public void printString() {
		try {
			while(isContinuePrint == true) {
				System.out.println("printString.....  threadName=" + Thread.currentThread().getName());
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void setIsContinuePrint(boolean isContinuePrint) {
		this.isContinuePrint = isContinuePrint;
	}

	@Override
	public void run() {
		printString();
	}
	
}
