package java8.lambda0;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		List<Apple> inventory = Arrays.asList(
				new Apple("red",120),
				new Apple("red",160),
				new Apple("green",130));
		
		System.out.println(" 1 : " + filterApple(inventory, new AppleHeavyWeightPredicate()));
		System.out.println(" 2 : " + filterApple(inventory, new AppleRedColorPredicate()));
		
		// 匿名类
		List<Apple> apples = filterApple(inventory, new ApplePredicate() {
			@Override
			public boolean test(Apple apple) {
				return 130 > apple.getWeight();
			}
		});
		System.out.println(" 3 : " + apples);
		
		// lambda 
		List<Apple> inventory2 = filterApple(inventory, (Apple a) -> "red".equals(a.getColor()));
		System.out.println(" lambda : " + inventory2);
		
		List<Apple> inventory3 = filter(inventory, (Apple a) -> "red".equals(a.getColor()));
		System.out.println(" lambda : " + inventory3);
		
		List<Integer> integers = Arrays.asList(1,2,5,7,8,12);
		List<Integer> evens = filter(integers, (Integer i) -> i%2==0);
		System.out.println(" odds : " + evens.toString());
		
		// 排序
		System.out.println(" 排序前： " + inventory.toString());
		inventory.sort((Apple a1, Apple a2) -> String.valueOf(a1.getWeight()).compareTo(String.valueOf(a2.getWeight())));
		System.out.println(" 排序后： " + inventory.toString());
		
		// lambda 
		Thread thread = new Thread(() -> System.out.println(Thread.currentThread().getName() + " run()"));
		thread.run();
	}
	
	/**
	 * 根据一定条件筛选
	 * @param inventory
	 * @param predicate
	 * @return
	 */
	public static List<Apple> filterApple(List<Apple> inventory, ApplePredicate predicate){
		List<Apple> list = new ArrayList<Apple>();
		for (Apple apple : inventory) {
			if (predicate.test(apple)) {
				list.add(apple);
			}
		}
		return list;
	}
	
	/**
	 * 加泛型的
	 * @param list
	 * @param p
	 * @return
	 */
	public static <T> List<T> filter(List<T> list, Predicate<T> p){
		List<T> result = new ArrayList<T>();
		for (T t : list) {
			if(p.test(t)) {
				result.add(t);
			}
		}
		return result;
	}
}	
