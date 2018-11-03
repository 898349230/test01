package com.ab.mypatern.simplefactory;

public class Test {
	public static void main(String[] args) {
		String name = "fish";
		Animal animal = Factory.getInstance(name);
		animal.run();
	}
}
