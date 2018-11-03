package java8.lambda2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import java8.lambda0.Apple;

public class Test {
	
	public static void main(String[] args) {
		
		// 三种函数式接口
		
		// Predicate
		Predicate<String> p = (String s) -> !s.isEmpty();
		List<String> strLis = Arrays.asList("1","","2","aa");
		List<String> strList = filter(strLis, p);
		System.out.println(strList);
		
		// Consumer
		List<Integer> intList = Arrays.asList(9,5,6,7,2,1,0,87);
		Consumer<Integer> c = (Integer i) -> System.out.println("consumer : " + i);
		forEach(intList, c);
		
		// Function
		List<String> strL = Arrays.asList("tom","jack","danny","jenny");
		List<Integer> retInt = map(strL, (String s) -> s.length());
		System.out.println("Function : " + retInt);
		
		// 变量 
		int portNum = 8082;
		Apple a = new Apple();
		Apple b = new Apple();
//		Runnable r = () -> {System.out.println(portNum);};  无法编译  portNum 需要 final修饰
//		Runnable r = () -> {System.out.println(a);};  // 无法编译  a 需要 final修饰
		a = b ;
		portNum = 8089;
		
		
		// 方法引用
		List<String> strList1 = filter(strLis, String :: isEmpty);
		System.out.println(" Predicate() 方法引用 ：  " + strList1);
		
		List<Integer> retInt2 = map(strL, String::length);
		System.out.println(" Function() 方法引用 ：  " + retInt2);
		
	}
	
	/**
	 * Predicate<T>
	 * 返回一个涉及T类型的布尔值
	 * 需要表示一个涉及类型T的布尔表达式时
	 */
	public static <T> List<T> filter(List<T> list, Predicate<T> p) {
		List<T> returnList = new ArrayList<T>();
		for (T t : list) {
			if(p.test(t)) {
				returnList.add(t);
			}
		}
		return returnList;
	}
	
	/**
	 * Consumer<T>
	 * 需要访问类型T的对象，并对其执行某些操作
	 * @param list
	 * @param c
	 */
	public static <T> void forEach(List<T> list, Consumer<T> c) {
		for (T t : list) {
			c.accept(t);
		}
	}  
	
	/**
	 * Function<T,R>
	 * 它接受一个泛型T的对象，并返回一个泛型R的对象
	 * @param list
	 * @param f
	 * @return
	 */
	public static <T,R> List<R> map(List<T> list, Function<T,R> f){
		List<R> result = new ArrayList<>(); 
		for (T t : list) {
			result.add(f.apply(t));
		}
		return result;
	}
	
}
