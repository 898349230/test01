package com.ab.regx;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegx {
	public static void main(String[] args) {
	//	System.out.println(true&&true);
		
		Pattern p = Pattern.compile("^[1-9]+\\d$");	
		Matcher matcher = p.matcher("45555a");
		boolean b = matcher.find();
//		boolean flag = Pattern.matches("[0-9]*", "3");
		System.out.println(b);
		
//		String num = "123456";
//		String rex = "^[1-9]+\\d*$"; //必须是1-9开头的。
//		Pattern p = Pattern.compile(rex);
//		Matcher m = p.matcher(num);
//		System.out.println(m.find());
	}
}
