package concurr2.ch3.join;

public class Test {

	public static void main(String[] args) {

		/** 
		 * join() 使所属线程对象x执行run()方法中的任务，而使当前线程z无限期的阻塞，直到 x 线程销毁在执行z的代码
		 * join() 有使线程排队的作用
		 * join 在内部使用wait方法等待
		 * 
		 */
		/** join(long)  方法会释放锁 */
		
//		test01();
		
		test02();
	}
	
	/**
	 * 可能出现意外  
	 * 输出结果为：
	 *  ThreadA begin...
	 *	ThreadA end...
	 *	main end
	 *	ThreadB begin...
	 *	ThreadB end...
	 * 
	 * join 后面的代码提前运行
	 * 问题发生在 join(2000)与ThreadB争夺锁时候是谁争夺到锁
	 * 
	 */
	public static void test01() {
		try {
			ThreadB b = new ThreadB();
			ThreadA a = new ThreadA(b);
			
			a.start();
			b.start();
			b.join(2000);
			System.out.println("main end");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 解释意外
	 */
	public static void test02() {
		ThreadB b = new ThreadB();
		ThreadA a = new ThreadA(b);
		
		a.start();
		b.start();
		System.out.println("main end");
	}
	
}
