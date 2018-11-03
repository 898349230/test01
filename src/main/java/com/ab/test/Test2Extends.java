package com.ab.test;

public class Test2Extends {
	
	public static void main(String[] args) {
		System.out.println("多态");
		Parent p = new Child();
		System.out.println(p.str);
		System.out.println(p.i);
		System.out.println(p.getString());
		
		System.out.println("非多态");
		Child c = new Child();
		System.out.println(c.str);
		System.out.println(c.i);
		System.out.println(c.getString());
		System.out.println(c.getParentString());
	}
}
class Parent{
	private String pStr;
	public String str= "parent";
	public int i =0;
	public String getString(){
		return str;
	}
}
class Child extends Parent{
	
	public String str= "child";
	public int i =1;
	public String getString(){
		return str;
	}
	public String getParentString(){
		return super.str;
	}
}
