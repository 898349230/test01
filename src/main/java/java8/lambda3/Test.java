package java8.lambda3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import java8.lambda0.Apple;
import java8.lambda0.Fruit;
import java8.lambda0.Orange;

public class Test {

	public static void main(String[] args) {
		
		List<String> str = Arrays.asList("a","b","A","B");
		System.out.println("排序前 str ：" + str);
		str.sort((String s1,String s2) -> s1.compareToIgnoreCase(s2));
		System.out.println("排序后 str :" + str);
		
		List<String> str2 = Arrays.asList("a","b","A","B");
		System.out.println("排序前 str2 ：" + str2);
		str2.sort(String :: compareToIgnoreCase);
		System.out.println("排序后 str2 ：" + str2);

		// 构造方法 
		// 空构造器     Supply<T>
		Supplier<Apple> c1 = Apple :: new;
		Apple a1 = c1.get();
		// 等价->
		Supplier<Apple> c2 = () -> new Apple();
		Apple a2 = c2.get();
		
		
		// 有参构造器  Function<T,R>
		Function<String, Apple> f = Apple :: new;
		Apple a3 = f.apply("红");
		System.out.println(" a3.getColor() ： " + a3.getColor());
		// 等价 --> 
		Function<String, Apple> f2 = (String color) -> new Apple(color);
		Apple a4 = f2.apply("红");
		System.out.println(" a4.getColor() ： " + a4.getColor());

		//  通过构造函数创建appleList
		List<String> weights = Arrays.asList("红的", "绿", "大红");
//		List<Apple> apples = map(weights, Apple :: new);
		List<Apple> apples = map(weights, f);
		for (Apple apple : apples) {
			System.out.println(" map苹果颜色: " + apple.getColor());
		}
		
		// 两个参数的构造方法 BiFunction<T, U, R>
		BiFunction<String, Integer, Apple> bf = Apple :: new;
		Apple a5 = bf.apply("红", 5);
		System.out.println(" a5.toString() : " + a5.toString());
		// 等价 -->
		BiFunction<String, Integer, Apple> bf2 = (String color, Integer weight) -> new Apple(color, weight);
		Apple a6 = bf2.apply("青", 2);
		System.out.println(" a6.toString() : " + a6.toString());
		
		// 自定义的三个参数的构造方法
		TriFunction<String, Integer, Integer, Orange> tf = Orange :: new ;
		Orange orange = tf.Apply("黄色", 150, 2);
		System.out.println(" orange.toString() : " + orange.toString());
		
		Fruit fruit = giveMeFruit("orange", "黄");
		System.out.println(" giveMeFruit : " + fruit.toString());
		
		
		/** 谓词复合  negate and or  让你可以重用已有的Predicate来创建更复杂的谓词 */
		
		Predicate<Apple> redApple = (Apple apple) -> apple.getColor().equals("red");
		// 不是红色的   negate
		Predicate<Apple> notRedApple = redApple.negate();
		Apple redA = new Apple("red");
		System.out.println(" 测试negate : " + redApple.test(redA));
		System.out.println(" 测试negate : " + notRedApple.test(redA));
		
		// 红色并且质量大于150的     and 
		Predicate<Apple> redAppleAndWeight =  redApple.and((Apple apple) -> apple.getWeight()>150);
		Apple redAndWeightApple1 = new Apple("red", 120);
		Apple redAndWeightApple2 = new Apple("red", 160);
		System.out.println(" 测试and " + redAppleAndWeight.test(redAndWeightApple1));
		System.out.println(" 测试and " + redAppleAndWeight.test(redAndWeightApple2));
		
		//红色并且质量大于150的  或者  绿色的
		Predicate<Apple> redAndWeightOrGreenApple = redApple.and(a -> a.getWeight() > 150)
				.or(a -> a.getColor().equals("green"));
		// and和or方法是按照在表达式链中的位置，从左向右确定优先级的。因此， a.or(b).and(c)可以看作(a || b) && c。

		
		/** 函数复合：按到andThen 和  compose*/
		
		Function<Integer, Integer> f1 = x -> x + 1;
		Function<Integer, Integer> g1 = x -> x * 2;
		// andThen 类似数学函数：  g1(f1(x))
		Function<Integer, Integer> h1 = f1.andThen(g1);
		// compose 类似数学函数：f1(g1())
		Function<Integer, Integer> h2 = f1.compose(g1);
		Integer at = h1.apply(1); // 结果是4 
		System.out.println(" andThen : " + at);
		Integer c = h2.apply(1); // 结果是4 
		System.out.println(" compose : " + c);
		
	}
	
	static Map<String, Function<String, Fruit>> map = new HashMap<>();
	static {
		map.put("red", Apple :: new);
		map.put("orange", Orange :: new);
	}
	public static Fruit giveMeFruit(String fruit, String color) {
		// 通过水果名称获取对应color的水果
		return map.get(fruit.toLowerCase()).apply(color);
	}
	
	
	public static List<Apple> map(List<String> list, Function<String, Apple> f) {
		List<Apple> result = new ArrayList<>();
		for (String e : list) {
			result.add(f.apply(e));
		}
		return result;
	}
}
