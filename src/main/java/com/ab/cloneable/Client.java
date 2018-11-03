package com.ab.cloneable;

/**
 * 测试克隆 
 * 内部的数组和引用对象才不拷贝，其他的原始类型比如int,long,String(Java就希望你把String认为是基本类型，String是没有clone方法的)等都会被拷贝的
 * @author Administrator
 *
 */
public class Client {
	public static void main(String[] args) {
		
		//测试类在克隆时只构造函数不会被执行
		Thing thing = new Thing();
		System.out.println("=============");
		Thing cloneThing = thing.clone();
	
		//测试浅克隆
		Thing2 thing2 = new Thing2();
		thing2.setValue("张三");
		
		Thing2 cloneThing2 = thing2.clone();
		cloneThing2.setValue("李四");
		//Object类提供的方法clone只是拷贝本对象，其对象内部的数组、引用对象等都不拷贝，
		//还是指向原生对象的内部元素地址，这种拷贝就叫做浅拷贝，
		System.out.println(thing2.getValue().toString());//[张三, 李四]

		//测试深克隆
		Thing3 thing3 = new Thing3();
		thing3.setValue("张三");
		
		Thing3 cloneThing3 = thing3.clone();
		cloneThing3.setValue("李四");
		//Object类提供的方法clone只是拷贝本对象，其对象内部的数组、引用对象等都不拷贝，
		//还是指向原生对象的内部元素地址，这种拷贝就叫做浅拷贝，
		System.out.println(thing3.getValue().toString());//[张三]


	}
}
