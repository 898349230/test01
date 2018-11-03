package concurr2.ch3.waitnotiy2;

public class Test {

	public static void main(String[] args) {
	
		String lock = "lock";
		
		ThreadAdd add = new ThreadAdd(lock);
		ThreadSubtract sub1 = new ThreadSubtract(lock);
		ThreadSubtract sub2 = new ThreadSubtract(lock);
		
		try {
			sub1.start();
			sub2.start();
			Thread.sleep(3000);
			add.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
}
