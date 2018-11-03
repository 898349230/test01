package concurr.ch3;

public class Singleton {

	/**
	 *  静态内部类 方式实现线程安全的单例
	 *  类的初始化期间，JVM会获取一个锁，这个线程可以同步多个线程对同一个类的初始化
	 *  @author Administrator
	 *
	 */
	
	private static class SingletonHandler {
		private static Singleton singleton = new Singleton();
	}
	
	private Singleton() {
	}
	
	public static Singleton getSingleton() {
		return SingletonHandler.singleton;
	}
	
}
