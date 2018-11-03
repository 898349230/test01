package concurr2.ch2.synnotextend;

public class Test {

	public static void main(String[] args) {
		
		// 如果sub中继承父类的synchronized的方法，sub的方法不是synchronized的，需要加synchronized关键字
		
		Sub sub = new Sub();
		ThreadA ta = new ThreadA(sub);
		ta.setName("A");
		ThreadB tb = new ThreadB(sub);
		tb.setName("B");
		
		ta.start();
		tb.start();
		
	}
}
