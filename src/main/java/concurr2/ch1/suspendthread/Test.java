package concurr2.ch1.suspendthread;


public class Test {

	public static void main(String[] args) {
		
		test1();
//		test2();
//		test3();
		
	}
	
	/**
	 * 测试suspend独占锁
	 * resume也会独占锁
	 */
	public static void test1() {
		final SynchronizeObj obj = new SynchronizeObj();
		
		Thread ta = new Thread() {
		@Override
		public void run() {
			obj.print();
		}};
		
		ta.setName("A");
		ta.start();
		
		Thread tb = new Thread() {
			@Override
			public void run() {
				System.out.println("B 线程启动了，准备进入 obj 的 print 方法");
				obj.print();
				System.out.println("B 线程启动了，进入obj的print方法后");
			}};
		tb.setName("B");
		tb.start();
		
	}
	
	/**
	 * 测试suspend独占锁,
	 * 不释放锁
	 * resume也会独占锁
	 */
	public static void test2() {
		
		try {
			MyThread thread = new MyThread();
			thread.start();
			Thread.sleep(3000);
			thread.suspend();
			// 如果 thread内含有 synchronize的println方法， “main end” 不会被打印
			System.out.println("main  end ");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 测试 suspend导致的数据不同步问题
	 * resume也会导致的数据不同步问题
	 * 
	 */
	public static void test3() {
		try {
			final MyObject obj = new MyObject();
			System.out.println("obj重新赋值前：" + obj);
			
			Thread thread = new Thread() {
				@Override
				public void run() {
					obj.setValue("b", "bb");
				}
			};
			
			thread.setName("A");
			thread.start();
			Thread.sleep(3000);
			System.out.println("obj重新赋值后：" + obj);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}