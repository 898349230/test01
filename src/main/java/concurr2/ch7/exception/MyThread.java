package concurr2.ch7.exception;

public class MyThread extends Thread{

	@Override
	public void run() {
		int[] arr = new int[] {1};
		System.out.println(arr[1]);
	}
	
}
