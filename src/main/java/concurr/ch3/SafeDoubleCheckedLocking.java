package concurr.ch3;

public class SafeDoubleCheckedLocking {
	// 
	
	/**
	 *  不是 volatile 修饰的 会重排序，volatile会生成内存屏障禁止指令重排序
	 *  
	 *  instance = new Instance()可拆分为以下三步：
	 *  
	 *  memory = allocate(); // 1：分配对象的内存空间
	 *  ctorInstance(memory); // 2：初始化对象
	 *  instance = memory; // 3：设置instance指向刚分配的内存地址
	 *  
	 *  步骤2,3可能重排序，导致一个线程在第一个 instance==null 处判断时判断为 !=null 
	 *  ,但是instance还没有初始化，而该线程会访问一个还没有初始化的对象
	 */
	private volatile static Instance instance;

	public static Instance getInstance() {
		if (instance == null) {
			synchronized (SafeDoubleCheckedLocking.class) {
				if (instance == null)
					instance = new Instance(); // instance为volatile，现在没问题了
			}
		}
		return instance;
	}
}