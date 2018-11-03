package concurr2.ch2.synobjectchange;

/**
 * 测试锁对象的改变
 * @author 
 *
 */
public class Test {

	public static void main(String[] args) {
		try {
			MyService service = new MyService();
			
			ThreadA ta = new ThreadA(service);
			ta.setName("A");
			ThreadB tb = new ThreadB(service);
			tb.setName("B");
			
			ta.start();
			// 此时main线程sleep 50 ms ，service中会把改变lock值，及 ta，tb不再使用同一个锁（ta的锁时 123，tb的锁变成了 456），两个线程会异步执行
			// 如果锁为对象的话，改变对象的属性不会造成异步，还是同一把锁
			Thread.sleep(50);
			tb.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}