package com.ab.thread;

public class PrintABC {
	public static void main(String[] args) throws InterruptedException {
		
		Object a = new Object();
		Object b = new Object();
		Object c = new Object();
		
		Print pa =new Print("A",c,a);
		Print pb = new Print("B",a,b);
		Print pc = new Print("C",b,c);
		new Thread(pa).start();
		Thread.sleep(100);
		new Thread(pb).start();
		Thread.sleep(100);
		new Thread(pc).start();
		Thread.sleep(100);
	//	Print A = new Print("A",c,a);
	}
}
class Print implements Runnable {
	private String name;
	private Object pre;
	private Object self;
	
	public Print(String name, Object pre, Object self) {
		this.name = name;
		this.pre = pre;
		this.self = self;
	}
	public void run() {
		int count = 10;
		while(count>0){
			synchronized (pre) {
				synchronized (self) {
					System.out.println(this.name);
					count--;
					self.notify();
					}
				
				try {
					pre.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}