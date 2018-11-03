package com.ab.reflect;

public class Car {
	
	private String bound;
	private int price;
	public static Integer INTEGER = 100;
	public static String STR = "teststr";
	
	public boolean flag;
	protected char c;
	long lon;
	
	public boolean run(int speed,String bound){
		System.out.println(bound+"  "+"汽车时速     :  "+speed);
		return true;
	}
	public boolean run(int speed){
		System.out.println("汽车时速     :  "+speed);
		return true;
	}
	
	People getDriver(){
		
		return new People();
	}
	
	protected void stop(){
		System.out.println("汽车stop");
	}
	private int getInteger(String str,int i){
		int a = 100;
		return i;
	}
	
	
	
	// constructor
	public Car(String bound, int price) {
		super();
		this.bound = bound;
		this.price = price;
	}
	//  setter&getter
	public String getBound() {
		return bound;
	}

	public void setBound(String bound) {
		this.bound = bound;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Car [bound=" + bound + ", price=" + price + "]";
	}
	
}
