package com.ab.compare;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Test {
	
	/**
	 * 测试compareTo   Person 实现 compare 接口
	 * 
	 * 一帮用于相同类对象之间的对比   自己与自己对比
	 */
	@org.junit.Test
	public void testCompareTo(){
		Random random = new Random();
		String[] names = {"赵XX","钱XX","孙XX","李XX","周XX"};
		int age = 0;
		List<Person> list = new ArrayList<>();
		Person[] ps = new Person[10];
		for(int i=0;i<10;i++){
			age = random.nextInt(10)+20;
			Person p = new Person(names[i%5],age);
			ps[i] = p;
			list.add(p);
		}
		
		Person temp = null;
		// 冒泡排序   根据age 降序排序
		for(int i=0;i<ps.length-1;i++){
			for(int j=0;j<ps.length-i-1;j++){
				if(ps[j].compareTo(ps[j+1])<0){
					temp = ps[j];
					ps[j] = ps[j+1];
					ps[j+1] = temp;
				}
			}
		}
		// 冒泡排序    根据age 升序排序
		for(int i=0;i<list.size()-1;i++){
			for(int j=0;j<list.size()-i-1;j++){
				if(list.get(j).compareTo(list.get(j+1))>0){
					temp = list.get(j);
					list.set(j, list.get(j+1));
					list.set(j+1, temp);
				}
			}
		}
		
		for (Person person : list) {
			System.out.println("list集合 ： "+person.getName()+"  "+person.getAge());
		}
		
		for (Person person : ps) {
			System.out.println("数组 : "+person.getName()+"  "+person.getAge());
		}
		
		// Person 实现了 Comnparable 接口  Arrays.sort() 升序排序 
		Arrays.sort(ps);
		for (Person person : ps) {
			System.out.println("Arrays.Sort : "+person.getName()+"  "+person.getAge());
		}
	}
	
	/**
	 * 测试comparator
	 * 
	 * 自定义排序类 实现Comparator接口 重写compare 方法 自定义排序规则
	 */
	@org.junit.Test
	public void testComparator(){
		Random random = new Random();
		String[] names = {"赵XX","钱XX","孙XX","李XX","周XX"};
		int age = 0;
		List<User> list = new ArrayList<>();
		User[] ps = new User[10];
		for(int i=0;i<10;i++){
			age = random.nextInt(10)+20;
			User p = new User(names[i%5],age);
			ps[i] = p;
			list.add(p);
		}

		//  自定义的 排序 (依据 age 升序，若相同  再依据 name升序)
		UserComparator comparator = new UserComparator();
		
		System.out.println("排序前 数组 ： ");
		for (User user : ps) {
			System.out.println(user.getName()+"  "+user.getAge()+"\t");
		}
		System.out.println();
		//  升序排序
		Arrays.sort(ps, comparator);
		System.out.println("排序后 数组 ： ");
		for (User user : ps) {
			System.out.println(user.getName()+"  "+user.getAge()+"\t");
		}
		System.out.println();
		
		System.out.println("排序前 list： ");
		for (User user : list) {
			System.out.println(user.getName()+"  "+user.getAge()+"\t");
			}
		System.out.println();		
		//  升序排序
		Collections.sort(list, comparator);
		System.out.println("排序前 list： ");
		for (User user : list) {
			System.out.println(user.getName()+"  "+user.getAge()+"\t");
		}
		
	}
}
