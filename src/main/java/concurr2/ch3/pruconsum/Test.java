package concurr2.ch3.pruconsum;

import concurr2.ch3.pruconsum.manytomany.C2;
import concurr2.ch3.pruconsum.manytomany.P2;
import concurr2.ch3.pruconsum.manytomany.ThreadC2;
import concurr2.ch3.pruconsum.manytomany.ThreadP2;
import concurr2.ch3.pruconsum.one2many.C4;
import concurr2.ch3.pruconsum.one2many.MyStack2;
import concurr2.ch3.pruconsum.one2many.P4;
import concurr2.ch3.pruconsum.one2many.ThreadC4;
import concurr2.ch3.pruconsum.one2many.ThreadP4;
import concurr2.ch3.pruconsum.onetone1.C;
import concurr2.ch3.pruconsum.onetone1.P;
import concurr2.ch3.pruconsum.onetone1.ThreadC;
import concurr2.ch3.pruconsum.onetone1.ThreadP;
import concurr2.ch3.pruconsum.onetone2.C3;
import concurr2.ch3.pruconsum.onetone2.MyStack;
import concurr2.ch3.pruconsum.onetone2.P3;
import concurr2.ch3.pruconsum.onetone2.ThreadC3;
import concurr2.ch3.pruconsum.onetone2.ThreadP3;

public class Test {

	public static void main(String[] args) {
		
//		 一生产，一消费：操作值
//		test01();
		
//		多生产，多消费：操作值  假死现象
//		test02();
		
//		一生产，一消费：消费栈
//		test03();
		
//		一生产，多消费：操作栈，解决wait条件改变与假死
//		test04();
//		test05();
		
//		多生产，一消费
//		test06();
		
//		 多生产，多消费：操作栈
		test07();
		
	}
	
	/**
	 * 一生产，一消费：操作值
	 */
	public static void test01() {
		String lock = "lock";
		P p = new P(lock);
		C c = new C(lock);
		ThreadP tp = new ThreadP(p);
		ThreadC tc = new ThreadC(c);
		tp.start();
		tc.start();
	}
	
	/**
	 * 多生产，多消费：操作值  假死现象
	 * 测试假死
	 * 
	 * 假死出现的主要原因可能是 notify 唤醒了同类（生产者唤醒了生产者。。。）
	 * 
	 * 解决假死的问题 使用 notifyAll()方法，唤醒所有wait的线程
	 * 
	 */
	public static void test02() {
		
		String lock = "lock";
		P2 p = new P2(lock);
		C2 c = new C2(lock);
		
		ThreadP2[] tp = new ThreadP2[2];
		ThreadC2[] tc = new ThreadC2[2];
		
		for(int i = 0; i < 2 ; i++) {
			
			tp[i] = new ThreadP2(p);
			tp[i].setName("生产者  " +(i + 1));
			tc[i] = new ThreadC2(c);
			tc[i].setName("消费者  " +(i + 1));
			tp[i].start();
			tc[i].start();
			
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Thread[] threadArray = new Thread[Thread.currentThread().getThreadGroup().activeCount()];
		Thread.currentThread().getThreadGroup().enumerate(threadArray);
		for(int i=0; i<threadArray.length; i++) {
			System.out.println(threadArray[i].getName() + "  " + threadArray[i].getState());
		}
	}
	
	/**
	 * 一生产，一消费：消费栈
	 */
	public static void test03() {
		
		MyStack stack = new MyStack();
		
		P3 p = new P3(stack);
		C3 c = new C3(stack);
		
		ThreadP3 tp = new ThreadP3(p);
		ThreadC3 tc = new ThreadC3(c);
		
		tp.start();
		tc.start();
	}
	
	/**
	 * 一生产，多消费：操作栈，解决wait条件改变与假死
	 * 
	 * 这个测试会报异常，因为MyStack中判断 list的size 是 用的if()判断，
	 * 当条件发生改变时，没有得到及时的响应，导致多个wait状态的线程被唤醒，解决办法将if()换成 while(), 会造成假死，再将notify改为notifyAll
	 * 详见  test05()
	 * 
	 * 
	 */
	public static void test04() {
		
		MyStack stack = new MyStack();
		
		P3 p = new P3(stack);
		C3 c = new C3(stack);
		
		ThreadP3 tp = new ThreadP3(p);
		ThreadC3 tc1 = new ThreadC3(c);
		ThreadC3 tc2 = new ThreadC3(c);
		ThreadC3 tc3 = new ThreadC3(c);
		ThreadC3 tc4 = new ThreadC3(c);
		ThreadC3 tc5 = new ThreadC3(c);
		ThreadC3 tc6 = new ThreadC3(c);
		ThreadC3 tc7 = new ThreadC3(c);
		
		tp.start();
		tc1.start();
		tc2.start();
		tc3.start();
		tc4.start();
		tc5.start();
		tc6.start();
		tc7.start();
	}
	
	/**
	 * 一生产，多消费：操作栈，解决wait条件改变与假死
	 * 
	 * 这个测试会报异常，因为MyStack中判断 list的size 是 用的if()判断，
	 * 当条件发生改变时，没有得到及时的响应，导致多个wait状态的线程被唤醒，解决办法将if()换成 while(), 会造成假死，再将notify改为notifyAll
	 * 
	 */
	public static void test05() {
		
		MyStack2 stack = new MyStack2();
		
		P4 p = new P4(stack);
		C4 c = new C4(stack);
		
		ThreadP4 tp = new ThreadP4(p);
		ThreadC4 tc1 = new ThreadC4(c);
		ThreadC4 tc2 = new ThreadC4(c);
		ThreadC4 tc3 = new ThreadC4(c);
		ThreadC4 tc4 = new ThreadC4(c);
		ThreadC4 tc5 = new ThreadC4(c);
		ThreadC4 tc6 = new ThreadC4(c);
		ThreadC4 tc7 = new ThreadC4(c);
		
		tp.start();
		tc1.start();
		tc2.start();
		tc3.start();
		tc4.start();
		tc5.start();
		tc6.start();
		tc7.start();
	}
	
	/**
	 * 多生产，一消费：操作栈
	 * 
	 */
	public static void test06() {
		MyStack2 stack = new MyStack2();
		P4 p = new P4(stack);
		C4 c = new C4(stack);
		ThreadP4 tp1 = new ThreadP4(p);
		ThreadP4 tp2 = new ThreadP4(p);
		ThreadP4 tp3 = new ThreadP4(p);
		ThreadP4 tp4 = new ThreadP4(p);
		ThreadP4 tp5 = new ThreadP4(p);
		ThreadP4 tp6 = new ThreadP4(p);
		ThreadP4 tp7 = new ThreadP4(p);
		
		ThreadC4 tc = new ThreadC4(c);
		tc.start();
		tp1.start();
		tp2.start();
		tp3.start();
		tp4.start();
		tp5.start();
		tp6.start();
		tp7.start();
		
	}
	
	/**
	 * 多生产，多消费：操作栈
	 * 
	 */
	public static void test07() {
		MyStack2 stack = new MyStack2();
		P4 p = new P4(stack);
		C4 c = new C4(stack);
		ThreadP4 tp1 = new ThreadP4(p);
		ThreadP4 tp2 = new ThreadP4(p);
		ThreadP4 tp3 = new ThreadP4(p);
		ThreadP4 tp4 = new ThreadP4(p);
		ThreadP4 tp5 = new ThreadP4(p);
		ThreadP4 tp6 = new ThreadP4(p);
		ThreadP4 tp7 = new ThreadP4(p);
		
		ThreadC4 tc1 = new ThreadC4(c);
		ThreadC4 tc2 = new ThreadC4(c);
		ThreadC4 tc3 = new ThreadC4(c);
		ThreadC4 tc4 = new ThreadC4(c);
		
		tp1.start();
		tp2.start();
		tp3.start();
		tp4.start();
		tp5.start();
		tp6.start();
		tp7.start();
		
		tc1.start();
		tc2.start();
		tc3.start();
		tc4.start();
		
	}
	
}
