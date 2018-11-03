package concurr2.ch4.reentrantlock2;

public class Test {

	public static void main(String[] args) {
		
		test01();
		
	}
	
	/**
	 * 测试reentrantlock
	 * 
	 * 调用lock.lock()代码的线程拥有了“对象监视器”，其他线程只能等待锁被释放后再次争抢锁，
	 * 效果和 使用 synchronize 关键字一样，线程之间还是顺序进行
	 * 
	 */
	public static void test01() {
		
		MyService service = new MyService();
		
		ThreadA a = new ThreadA(service);
		a.setName("A");
		a.start();
		
		ThreadAA aa = new ThreadAA(service);
		aa.setName("AA");
		aa.start();
		
		ThreadB b = new ThreadB(service);
		b.setName("B");
		b.start();
		
		ThreadBB bb = new ThreadBB(service);
		bb.setName("BB");
		bb.start();
		
	}
}
