package concurr2.ch7.printabc;

public class MyThread extends Thread{
	
	private Object lock;
	private String showChar;
	// 1 代表打印A， 2打印B， 0打印C
	private int showNumPosition;
	private int printCount = 0; // 统计打印了几个字母
	private volatile static int addNumber = 1; 
	
	public MyThread(Object lock, String showChar, int showNumPosition) {
		super();
		this.lock = lock;
		this.showChar = showChar;
		this.showNumPosition = showNumPosition;
	}

	@Override
	public void run() {
		synchronized(lock) {
			try {
				while(true) {
					if(addNumber % 3 == showNumPosition) {
						System.out.println(Thread.currentThread().getName() + " " + showChar +
								" addNumber = " + addNumber);
						lock.notifyAll();
						addNumber++;
						printCount++;
						if(printCount == 3) {
							break;
						}
					}
					lock.wait();
				}
			} catch (Exception e) {
			}
		}
		
	}
}
