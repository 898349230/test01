package concurr.ch8.cyclicbarrier;

import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * CyclicBarrier可以用于多线程计算数据，最后合并计算结果的场景
 * 
 * @author
 *
 */
public class BankWaterService implements Runnable {

	/** 假设有四个sheet需要统计，每个统计完成后在执行 BankWaterService 的 run()方法 */
	private CyclicBarrier c = new CyclicBarrier(4, this);
	/** 保存每个 sheet 计算出来的结果 */
	private ConcurrentHashMap<String, Integer> sheetBankWaterCount = new ConcurrentHashMap<>();
	/** 假设有4个 sheet，需要4个线程同时进行计算 */
	private Executor executor = Executors.newFixedThreadPool(4);

	private void count() {
		for (int i = 0; i < 4; i++) {
			executor.execute(new Runnable() {
				@Override
				public void run() {
					// 计算 sheet， 计算代码省略
					sheetBankWaterCount.put(Thread.currentThread().getName(), 1);
						// 计算完成，插入一个屏障
						try {
							c.await();
						} catch (InterruptedException | BrokenBarrierException e) {
							e.printStackTrace();
						}
				}
			});
		}
	}
	
	@Override
	public void run() {
		int result = 0;
		Set<Entry<String, Integer>> entrySet = sheetBankWaterCount.entrySet();
		for (Entry<String, Integer> entry : entrySet) {
			result += entry.getValue();
		}
		System.out.println(result);
	}

	public static void main(String[] args) {

		BankWaterService service = new BankWaterService();
		service.count();
	}

}
