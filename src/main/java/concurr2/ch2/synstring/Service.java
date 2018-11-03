package concurr2.ch2.synstring;

public class Service {

	private Object obj;
//	static
	public void print(String str) {
		try {
			synchronized(str) {
				while(true) {
					System.out.println(Thread.currentThread().getName());
					Thread.sleep(1000);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	} 
		
//	static
	public void print2(Object obj) {
		try {
			synchronized(obj) {
				while(true) {
					System.out.println(Thread.currentThread().getName());
					Thread.sleep(1000);
				}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} 
}
