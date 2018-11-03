package concurr2.ch2.volatil;

/**
 * volatile 效率比 synchronize 高
 * volatile 可以保证数据的可见性，不能保证数据的原子性
 * volatile 只能修饰变量
 * volatile 解决的时多个线程对同一个变量的可见性， synchronize解决的时多个线程对数据的同步性
 * 
 * volatile 只是JVM虚拟机保证从主内存加载到线程内存中的数据时最新的
 * 
 * synchronized 不仅可以解决进入一个线程看到对象不一致的状态，
 * 还可以保证进入同步方法，同步代码块的每个线程，都看到由同一个锁保护之前所有的修改效果
 * 
 * 线程安全包括原子性和可见性
 * 
 * @author 
 *
 */
public class Test {

	public static void main(String[] args) {
		
		test02();
		
	}
	
	/**
	 * 测试 volatile不同步
	 */
	public static void test02() {
		
		MyThread[] threads = new MyThread[100];
		for(int i=0; i < 100; i++) {
			threads[i] = new MyThread();
		}
		
		for(int i=0; i < 100; i++) {
			threads[i].start();
		}
		
	}
	
	public static void test01() {
		try {
			PrintString printString = new PrintString();
			new Thread(printString).start();
			System.out.println("停止线程 stopThread= " + Thread.currentThread().getName());
			printString.setIsContinuePrint(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
