package concurr.ch4.threadpool;

public interface ThreadPool<Job extends Runnable>{

	/** 执行一个Job，该Job需要实现 Runnable接口 */
	void execute(Job job);
	/** 关闭线程池 */
	void shutDown();
	/** 增加任务 */
	void addWorkers(int num);
	/** 移除任务 */
	void removeWorkers(int num);
	/** 获取当前job数 */
	int getJobSize();
	
}
