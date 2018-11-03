package concurr2.ch1.curthread;

public class MyThread extends Thread{

	public MyThread() {
		System.out.println("构造方法   Thread.currentThread().isAlive()  " + Thread.currentThread().isAlive());
		System.out.println("构造方法 。。。。： Thread.currentThread().getName() = " + Thread.currentThread().getName());
		System.out.println("构造方法   this.isAlive()  " + this.isAlive());
		System.out.println("构造方法 。。。。： this.getName() = " + this.getName());

	}
	
	@Override
	public void run() {
		System.out.println("run方法   Thread.currentThread().isAlive()  " + Thread.currentThread().isAlive());
		System.out.println("run方法 。。。。： Thread.currentThread().getName() = " + Thread.currentThread().getName() );
		System.out.println("run方法   this.isAlive()  " + this.isAlive());
		System.out.println("run方法 。。。。： this.getName() = " + this.getName() );
		System.out.println("run方法 。。。。： this.getId() = " + this.getId() );
		System.out.println("run方法 。。。。： Thread.currentThread().getId() = " + Thread.currentThread().getId() );
	}

}
