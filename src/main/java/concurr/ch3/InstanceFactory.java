package concurr.ch3;

/**
 * 在执行类的初始化期间，JVM会去获取一个锁。
 * 这个锁可以同步多个线程对同一个类的初始化
 * @author 
 *
 */
public class InstanceFactory {
	
	private static class InstanceHolder {
//		static {
//			System.out.println(" InstanceHolder static code ");
//		}
		private static Instance instance = new Instance();
	}
	
	public static Instance getInstance() {
		Instance instance = InstanceHolder.instance;
		return instance; // 这里将导致InstanceHolder类被初始化
	}
	
	public static void main(String[] args) {
		InstanceFactory.getInstance();
	}
}