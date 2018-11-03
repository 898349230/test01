package concurr2.ch1.extendhread;

public class Test {

	public static void main(String[] args) {
		try {
			MyThread thread = new MyThread();
			thread.setName(" Mythread ");
			thread.start();
			for (int i = 0; i < 10; i++) {
				int time = (int) (Math.random() * 1000);
				Thread.sleep(time);
				System.out.println("run = " + Thread.currentThread().getName() + " i= " + i);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
