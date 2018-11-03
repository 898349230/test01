package concurr2.ch4.condition3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Run {

	volatile private static int nextPrintWho = 1;
	final private static ReentrantLock lock = new ReentrantLock();
	final private static Condition conditionA = lock.newCondition();
	final private static Condition conditionB = lock.newCondition();
	final private static Condition conditionC = lock.newCondition();
	
	public static void main(String[] args) {
		
		/** 使用Condition对象对线程执行的业务进行排序规划 */
		
		Thread threadA = new Thread() {
			@Override
			public void run() {
				try {
					lock.lock();
					while(nextPrintWho != 1) {
						conditionA.await();
					}
					for(int i=0; i < 3 ; i++) {
						System.out.println("ThreadA " + (i+1));
					}
					nextPrintWho = 2;
					conditionB.signalAll();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
		};
		
		Thread threadB = new Thread() {
			@Override
			public void run() {
				try {
					lock.lock();
					while(nextPrintWho != 2) {
						conditionB.await();
					}
					for(int i=0; i < 3 ; i++) {
						System.out.println("ThreadB " + (i+1));
					}
					nextPrintWho = 3;
					conditionC.signalAll();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
		};
		
		Thread threadC = new Thread() {
			@Override
			public void run() {
				try {
					lock.lock();
					while(nextPrintWho != 3) {
						conditionC.await();
					}
					for(int i=0; i < 3 ; i++) {
						System.out.println("ThreadC " + (i+1));
					}
					nextPrintWho = 1;
					conditionA.signalAll();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
		};
		
		Thread[] aArr = new Thread[5];
		Thread[] bArr = new Thread[5];
		Thread[] cArr = new Thread[5];
		
		for(int i=0; i<5; i++) {
			aArr[i] = new Thread(threadA);
			bArr[i] = new Thread(threadB);
			cArr[i] = new Thread(threadC);
			
			aArr[i].start();
			bArr[i].start();
			cArr[i].start();
		}
		
	}
	
}
