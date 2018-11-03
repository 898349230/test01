package com.ab.mypatern.single;

public class Test extends Thread{
	
	@Override
	public void run() {
		System.out.println(Singleton2.getInstance().hashCode());
	}

	public static void main(String[] args) {

		Test[] test = new Test[10];
		for(int i=0;i<10;i++){
			 test[i] = new Test();
		}
		for (Test t : test) {
			t.start();
		}
		
	}
}
