package testpro.test01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TestList {
	public static void main(String[] args) {
		List l = new ArrayList();
		l.add(1);
		l.add(1);
		l.add(1);
		l.add(null);
		l.add(1);
		l.add(null);
		l.add(1);
		Object object = l.get(3);
		System.out.println(l.size());
		l.remove(null);
		System.out.println(l.size());
		
		int a = -2;
		int b = -100;
		System.out.println(" a=  " + a);
		System.out.println(" b=  " + b);
		a = a << 2;
		b = b >>> 4;
		System.out.println(" a=  " + a);
		System.out.println(" b=  " + b);
		
		
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		list1.add("1");
		list1.add("2");
		list1.add("3");
		list1.add("4");
		list1.add("5");
		
		// 阿里规范list转为数组
		String[] array = new String[list1.size()];
		array = list1.toArray(array);
		
		List<String> asList = Arrays.asList(array);
		asList.add("ass");
		
		list2.add("2");
		list2.add("3");
		list2.add("4");
		list2.add("9");
		
	}
}
