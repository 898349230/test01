package concurr2.ch3.waitnotifyaction;

public class DBTools {

	/**
	 * 交替打印的标记
	 */
	volatile private boolean preIsA = false;
	
	synchronized public void backupA() {
		try {
			while(preIsA) {
				this.wait();
			}
			for(int i =0 ; i < 5; i++) {
				System.out.println("备份A。。。");
			}
			preIsA = true;
			this.notifyAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	synchronized public void backupB() {
		try {
			while(!preIsA) {
				this.wait();
			}
			for(int i =0 ; i < 5; i++) {
				System.out.println("备份B。。。");
			}
			preIsA = false;
			this.notifyAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
