package concurr2.ch2.synstring;

import concurr2.ch2.synstring.Service;
import concurr2.ch2.synstring.ThreadA;
import concurr2.ch2.synstring.ThreadB;

public class Test {

	public static void main(String[] args) {
		
		/**
		 * 只有一个线程启动，因为synchronizede(str) 内的str 都是 字符串 “AA” , 
		 * 内存地址是同一个，所有会被认为是通一把锁，另一个线程会等待这个锁
		 * 
		 * 大多数情况下synchronizede() 代码块不使用String
		 * 如果使用的synchronizede(object)的话，则使用的是两个锁，会循环打印
		 * 
		 */
		Service service = new Service();
		
		ThreadA ta = new ThreadA(service);
		ta.setName("A");
		ThreadB tb = new ThreadB(service);
		tb.setName("B");
		
		ta.start();
		tb.start();
		
	}
	
	
}