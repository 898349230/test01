package com.ab.test;

import java.io.UnsupportedEncodingException;

public class TestCharset {
	public static void main(String[] args) {
		String str = "中国";
		try {
			byte[] bytes = str.getBytes("gbk");
			String s = new String(bytes,"gbk");
			System.out.println(s);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
