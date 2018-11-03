package com.ab.thread;
/**
 * 子线程循环10次，接着主线程循环100，接着又回到子线程循环10次，接着再回到主线程又循环100，如此循环50次
 * @author Administrator
 *
 */
public class ThreadTest {
	public static void main(String[] args) {
		new ThreadTest().init();
	}
	
	public void init(){
		
		final Bussiness bussiness = new Bussiness();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=0;i<50;i++){
					bussiness.subThread(i);
				}
			}
		}).start();
		
		for(int i =0;i<50;i++){
			bussiness.mainThread(i);
		}
	}
	
	private class Bussiness {
		
		boolean bShouldSub = true;//这里相当于定义了控制该谁执行的一个信号灯
		
		public synchronized void mainThread(int i){
			if(bShouldSub){
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			for(int j=0;j<5;j++){
				System.out.println(Thread.currentThread().getName()+"\ti="+i+"\tj="+j);
			}
			bShouldSub = true;
			this.notify();
		}
		
		public synchronized void subThread(int i){
			if(!bShouldSub){
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			for(int j=0;j<10;j++){
				System.out.println(Thread.currentThread().getName()+"\ti="+i+"\tj="+j);
			}
			bShouldSub = false;
			this.notify();
		}
	}
}
