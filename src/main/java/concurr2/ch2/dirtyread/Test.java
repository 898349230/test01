package concurr2.ch2.dirtyread;

public class Test {

	public static void main(String[] args) {
		try {
			PublicVar var = new PublicVar();
			MyThread thread = new MyThread(var);
			thread.start();
			Thread.sleep(3000);
			// 如果getValue()方法未synchronized，输出的值会与 main线程sleep的时长有关系
			var.getValue();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
