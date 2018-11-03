package java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

/**
 * 使用流
 * @author 
 *
 */
public class Test01 {

	public static void main(String[] args) {
		
		List<Dish> menu = Arrays.asList(
				new Dish("pork", false, 800, Dish.Type.MEAT),
				new Dish("beef", false, 700, Dish.Type.MEAT),
				new Dish("chicken", false, 400, Dish.Type.MEAT),
				new Dish("french fries", true, 530, Dish.Type.OTHER),
				new Dish("rice", true, 350, Dish.Type.OTHER),
				new Dish("season fruit", true, 120, Dish.Type.OTHER),
				new Dish("pizza", true, 550, Dish.Type.OTHER),
				new Dish("prawns", false, 300, Dish.Type.FISH),
				new Dish("salmon", false, 450, Dish.Type.FISH) );
		
		// 流  -> 内部迭代
		// 集合 -> 外部迭代
		
		/** 映射  */
		List<String> names = (List<String>) menu.stream()
				.filter(d -> d.getCalories() > 300)
				.map(Dish::getName)  // 它会接受一个函数作为参数。这个函数会被应用到每个元素上，并将其映射成一个新的元素,(使用映射一词，是因为它和转换类似，但其中的细ॱ差别在于它是“创建一个新版本”而不是去“修改”)
//				.limit(3)
				.collect(Collectors.toList());
		System.out.println(" 卡路里 大于 300 的 ： " + names);
		
		List<Integer> namesLength = (List<Integer>) menu.stream()
				.filter(d -> d.getCalories() > 300)
				.map(Dish::getName)  // 它会接受一个函数作为参数。这个函数会被应用到每个元素上，并将其映射成一个新的元素,(使用映射一词，是因为它和转换类似，但其中的细ॱ差别在于它是“创建一个新版本”而不是去“修改”)
				.map(String::length)
//				.limit(3)
				.collect(Collectors.toList());
		System.out.println(" 卡路里 大于 300 的名称的长度 ： " + namesLength);
		
		List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4, 6, 10);
		List<Integer> list = numbers.stream()
						.filter(i -> i % 2 == 0)
						.distinct()   // 返回一个元素各异（根据流所生成元素的hashCode和equals方法实现）的流
						.limit(3)   // 截断流  该方法会返回一个不超过给定长度的流
						.skip(1)  // 跳过元素  跳过前两个
						.collect(Collectors.toList());
		System.out.println("偶数 ： " + list);
		
//		numbers.stream()
//		.forEach(System.out::println);

		
		/** 扁平化流 */  
		List<String> words = Arrays.asList("hello", "word");
		List<String[]> wordList = words.stream()
							.map(word -> word.split(""))
							.distinct()
							.collect(Collectors.toList());
		for (String[] strings : wordList) {
			System.out.println();
			for (String s : strings) {
				System.out.print(s);
			}
		}
		
		List<Stream<String>> wordsStream = words.stream()
				.map(word -> word.split(""))  // 将每个单次转为由其字母组成的数组
				.map(Arrays::stream)   // 将数组转为流，将每个数组分别转换
				.distinct()
				.collect(Collectors.toList());
		System.out.println(" Arrays::stream " + wordsStream);
		
		// flatMap 将流扁平化
		List<String> wordList2 = words.stream()
			.map(word -> word.split(""))
			.flatMap(Arrays::stream)  // flyMap 将各个生成流扁平化为一个流，各个数组并不是分别映射成一个流，而是映射成流的内容
			.distinct()
			.collect(Collectors.toList());
		System.out.print(" flatMap 扁平化 :　 ");
		for (String s : wordList2) {
			System.out.print(s);
		}
		System.out.println();
		
		// 给定列表[1, 2, 3]和列表[3, 4]，
		// 返回[(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]
		List<Integer> numbers1 = Arrays.asList(1, 2, 3);
		List<Integer> numbers2 = Arrays.asList(3, 4);
		List<int[]> collect = numbers1.stream()
		.flatMap(i -> numbers2.stream()
							.map(j -> new int[]{i, j})
				)
		.collect(Collectors.toList());
		System.out.println(" flatMap2: ");
		for (int[] is : collect) {
			System.out.println(is[0] + "," + is[1]);
		}
		
		System.out.println("flatMap1:");
		
		// 返回 和 能够被3整除的 组合
		List<int[]> collect2 = numbers1.stream()
		.flatMap(i -> numbers2.stream()
						.filter(j -> (i + j)%3 == 0)
						.map(j -> (new int[] {i, j}))
				)
		.collect(Collectors.toList());
		for (int[] is : collect2) {
			System.out.println(is[0] + "," + is[1]);
		}
		
		/** 查找与匹配   allMatch、 anyMatch、 noneMatch、 findFirst和findAny  */
		// anyMatch 流中是否有一个元素能匹配给定的谓词
		boolean anyMatch = menu.stream().anyMatch(Dish::isVegetarian);
		System.out.println("anyMatch : " + anyMatch);
		
		// allMatch 都匹配
		boolean allMatch = menu.stream().allMatch(t -> t.getCalories() < 10000);
		System.out.println(allMatch);
		
		// noneMatch 都不匹配
		boolean noneMatch = menu.stream().noneMatch(t -> t.getCalories() >= 10000);
		System.out.println(noneMatch);
		
		// findAny 方法将返回当前流中的任意元素
		Optional<Dish> optional = menu.stream()
				.filter(Dish::isVegetarian)
				.findAny();
		
		// isPresent  optional存在值的时候返回true,不存在返回false
		boolean present = optional.isPresent(); // optional存在值的时候返回true,不存在返回false
		System.out.println("present : " + present);
		
		// get 会在值存在时返回值，否则抛出一个NoSuchElement异常
		Dish dish = optional.get();
		System.out.println("get : " + dish);
		
		// 会在值存在时返回值，否则返回一个默认值
		Dish elseDish = optional.orElse(new Dish("golf", false, 700, Dish.Type.MEAT));
		System.out.println("orElse : " + elseDish);
		
		menu.stream()
			.filter(Dish::isVegetarian)
			.findAny() 
			// ifPresent : 值存在的时候执行给定的代码块
			.ifPresent(d -> System.out.println("findAny ifPresent ： " + d.getName()));  
	
		// findFirst  找到第一个元素
		List<Integer> nums = Arrays.asList(1, 2, 3, 4, 6);
		nums.stream()
			.map(x -> x * x)
			.filter(x -> x % 3 == 0)
			.findFirst()
			.ifPresent(x -> System.out.println(" findFirst 第一个被3整除的 ： " + x));
		
		/** 规约  ： 将流中的元素结合起来  */
		Integer sum = nums.stream().reduce(0, (a, b) -> a + b); // 第一个参数表示起始值
		// 使用方法引用，Integer静态的sum方法对两个数求和
		Integer sum2 = nums.stream().reduce(0, Integer::sum);
		System.out.println("规约 reduce ： " + sum);
		System.out.println("规约 reduce ： " + sum2);
		
		// 求最大值和最小值
		Optional<Integer> maxOp = nums.stream().reduce(Integer::max);
		maxOp.ifPresent(max -> System.out.println("reduce 最大值： " + max));
		Optional<Integer> minOp = nums.stream().reduce((x, y) -> x < y ? x : y);
		minOp.ifPresent(min -> System.out.println("reduce 最小值： " + min));
		
		/** 数值流 */
		
		// 对象流 -> 数值流
		IntStream mapToInt = menu.stream()
			.mapToInt(Dish::getCalories);
		OptionalInt minOpInt = mapToInt.min(); // sum(),max()
		minOpInt.ifPresent(min -> System.out.println("数值流：" + min));
		
		// 数值流 -> 对象流
		IntStream mapToInt2 = menu.stream()
				.mapToInt(Dish::getCalories);
		Stream<Integer> intToMap = mapToInt2.boxed();
		intToMap.forEach(a -> System.out.println("数值流 -> 对象流 ：" + a));
		
		
		OptionalInt maxOp2 = menu.stream()
			.mapToInt(Dish::getCalories)
			.max();
		// 显示设置默认值 ，如果 maxOp2 没有值，会取 3 为默认值
		int max = maxOp2.orElse(3); 
		System.out.println("orElse : " + max);
		
		// 取值范围，包含100
		long count = IntStream.rangeClosed(1, 100)
						.filter(i -> i % 2 == 0)
						.count();
		
		// 取值范围，不包含100
		long count2 = IntStream.range(1, 100)
			.filter(i -> i % 2 ==0)
			.count();
		System.out.println(" 1-100  偶数的个数  rangeClosed ：" + count);
		System.out.println(" 1-100  偶数的个数  range ：" + count2);
		
		
	}

}
