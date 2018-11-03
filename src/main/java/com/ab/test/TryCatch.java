package com.ab.test;

public class TryCatch {
	public static void main(String[] args) throws Exception {
		//System.out.println(test01());
		try{
			//int i = 10/0;
			throw new Exception("aaaaaaaaaaaaaa");
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("ssssssssssssssss");
	}
	
	public static int test(){
		try{
			return fun1();
		}catch (Exception e) {

		}finally {
			return fun2();
		}
	}
	public static int fun1(){
		System.out.println("fun1");
		return 1;
	}
	public static int fun2(){
		System.out.println("fun2");
		return 2;
	}
}
