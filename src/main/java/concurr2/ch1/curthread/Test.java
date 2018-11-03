package concurr2.ch1.curthread;

public class Test {
	public static void main(String[] args) {
		MyThread run = new MyThread();
		run.setName(" A ");
		// 构造方法是有 main 线程调用， run方法是又 线程创建
		// isAlive() : 判断当前线程是否允许
		System.out.println("main 方法  start前： run.isAlive() " + run.isAlive());
		run.start();
		System.out.println(" id " + Thread.currentThread().getId() );
//		run.sleep(3000);
//		run.run();
		Thread t = new Thread(run);
		t.setName(" B ");
//		t.start();
		
		System.out.println("main 方法  start后：   run.isAlive()  " + run.isAlive());
	}
}
