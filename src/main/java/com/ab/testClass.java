package com.ab;

import java.util.HashSet;
import java.util.Set;

public class testClass {
	public static void main(String[] args) {
		Set<String> s = new HashSet<>();
		String a = "a";
//		String aa = "a";
		String b = "b";
		String c = a+"b";
		String d = a+b;
		s.add("a");
		s.add(a);
//		s.add(aa);
		System.out.println(s.size());
		System.out.println(a.hashCode());
		System.out.println(b.hashCode());
		System.out.println(c.hashCode());
		System.out.println(d.hashCode());
	}
}
