package com.ab.produce;

public class Test1 {
	public static void main(String[] args) {
		Apple apple = new Apple();
		for(int i=0;i<50;i++){
			new Thread(new Produce(apple)).start();;
			new Thread(new Consume(apple)).start();;
		}
	}
}

class Produce implements Runnable{
	Apple apple ;

	public Produce(Apple apple) {
		super();
		this.apple = apple;
	}
	@Override
	public void run() {
		String color = "红色";
		this.apple.produce(color);
	}
}
class Consume implements Runnable{
	Apple apple ;
	public Consume(Apple apple) {
		super();
		this.apple = apple;
	}
	public void run() {
		this.apple.consume();
	}
}
class Apple{
	private String color;
	private boolean flag=true;
	
	public synchronized void produce(String color){
		if(!flag){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("生产了"+color+"的苹果");
		this.color = color;
		this.flag = false;
	}
	
	public synchronized void consume(){
		if(flag){
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("消费了"+this.color+"的苹果");
		this.flag = true;
	}
}
