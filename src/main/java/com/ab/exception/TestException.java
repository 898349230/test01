package com.ab.exception;

public class TestException {

	public static void main(String[] args) {
		//System.out.println(print());
		test();
	}
	public static int print(){
		try {
			System.out.println("try");
			return 1;
		} catch (Exception e) {
			System.out.println("catch");
			return 2;
		}finally{
			System.out.println("finally");
		}
	}
	
	public static void test(){
		try{
			generateException();
			System.out.println("e");
		}catch(NumberFormatException ex){
			System.out.print("j");
		}catch(ArrayIndexOutOfBoundsException a){
			System.out.println("throw ArrayIndexOutOfBoundsException()");
		}
		finally{
			System.out.print("m");
		}
		
	}
	
	public static void generateException(){
		System.out.println("generateException()");
		throw new ArrayIndexOutOfBoundsException();
		//System.out.print("h");   //�﷨����
	}
}
