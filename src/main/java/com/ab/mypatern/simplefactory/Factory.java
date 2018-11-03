package com.ab.mypatern.simplefactory;

public class Factory {
	public static Animal getInstance(String name){
		if(name.equalsIgnoreCase("dog")){
			return new Dog();
		}else if(name.equalsIgnoreCase("fish")){
			return new Fish();
		}else{
			System.out.println("没有该类型动物");
			return null;
		}
	}
}
