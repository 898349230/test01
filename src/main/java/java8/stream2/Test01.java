package java8.stream2;

import static java.util.stream.Collectors.averagingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.summarizingInt;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
//import java.util.stream.Collectors;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import java8.stream.Dish;
import java8.stream.Dish.Type;


/**
 *  收集器
 *  所有收集器整理： P129
 * @author 
 *
 */
public class Test01 {

	public enum CaloricLevel { DIET, NORMAL, FAT }
	
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
		
		long dishNum1 = menu.stream().count();
		Long dishNum2 = menu.stream().collect(counting());
		System.out.println("菜肴数量 ：" + dishNum1 + "\t" + dishNum2);

		// 定义一个 Comparator 
		Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
		// maxBy
		Optional<Dish> maxCaloriesDish = menu
										.stream()
										.collect(maxBy(dishCaloriesComparator));
		maxCaloriesDish.ifPresent(dish -> System.out.println("maxBy -> 热量最高的：" + dish.toString()));
		
		// summingInt 求和
		Integer sumCalories = menu.stream().collect(summingInt(Dish::getCalories));
		System.out.println("summingInt -> 求和： " + sumCalories);
		
		// averagingInt 平均数
		Double average = menu.stream().collect(averagingInt(Dish::getCalories));
		System.out.println("averagingInt -> 平均：" + average);
		
		// statics 统计       IntSummaryStatistics{count=9, sum=4200, min=120, average=466.666667, max=800}
		IntSummaryStatistics statics = menu.stream().collect(summarizingInt(Dish::getCalories));
		System.out.println("statics -> 统计：" + statics);
		
		// join : 字符串拼接
		String joinString = menu.stream().map(Dish::getName).collect(joining(" , "));
		System.out.println("joinString -> 字符串拼接： " + joinString);
		
		// groupingBy : 分组      {FISH=[prawns, salmon], MEAT=[pork, beef, chicken], OTHER=[french fries, rice, season fruit, pizza]}
		Map<Type, List<Dish>> group1 = menu.stream().collect(groupingBy(Dish::getType));
		System.out.println("groupingBy -> 分组： " + group1);

		// 自定义分组
		Map<CaloricLevel, List<Dish>> group2 = menu.stream().collect(groupingBy(dish -> {
				if(dish.getCalories() <= 400){
					return CaloricLevel.DIET;
				} else if(dish.getCalories() >= 700) {
					return CaloricLevel.FAT;
				}else {
					return CaloricLevel.NORMAL;
				}
			} ));
		System.out.println("groupingBy -> 自定义分组 ： " + group2);
		
		// 多级分组
		Map<Type, Map<CaloricLevel, List<Dish>>> group3 = menu.stream().collect(groupingBy(Dish::getType,groupingBy(
				dish -> {
					if(dish.getCalories() <= 400) {
						return CaloricLevel.DIET;
					} else if(dish.getCalories() >= 700) {
						return CaloricLevel.FAT;
					}else {
						return CaloricLevel.NORMAL;
					}
				}
				)));
		System.out.println("groupingBy -> 二级分组 ：" + group3);
		
		
		// 按子组收集数据
		// groupingBy(f, toList())， 传递给第一个groupingBy的第二个收集器可以是任何类型
		Map<Dish.Type, Long> typesCount = menu.stream().collect(
				groupingBy(Dish::getType, counting()));
		System.out.println("groupingBy -> typesCount ：" + typesCount);
		
		
		Map<Dish.Type, Optional<Dish>> mostCaloricByType = menu.stream()
											.collect(groupingBy(Dish::getType,
											maxBy(Comparator.comparingInt((Dish::getCalories)))));
		// {FISH=Optional[salmon], MEAT=Optional[pork], OTHER=Optional[pizza]}
		System.out.println("mostCaloricByType : " + mostCaloricByType );
		
		// 把收集器返回的结果转换为另一种类型，你可以使用 Collectors.collectingAndThen工厂方法返回的收集器
		Map<Dish.Type, Dish> mostCaloricByType2 = menu.stream()
									.collect(groupingBy(Dish::getType,
											collectingAndThen(
													maxBy(Comparator.comparingInt(Dish::getCalories)),
													Optional::get)
													)
											);
		// {FISH=salmon, MEAT=pork, OTHER=pizza}
		System.out.println("mostCaloricByType2 : " + mostCaloricByType2 );
		
		Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType =
				menu.stream().collect(
				groupingBy(Dish::getType, mapping(
					dish -> { if (dish.getCalories() <= 400) return CaloricLevel.DIET;
					else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
					else return CaloricLevel.FAT; },
					toSet() 
						)
					));
		// {FISH=[DIET, NORMAL], MEAT=[DIET, FAT, NORMAL], OTHER=[DIET, NORMAL]}
		System.out.println("caloricLevelsByType : " + caloricLevelsByType);
		
		Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType2 =
				menu.stream().collect(
					groupingBy(Dish::getType, mapping(
						dish -> { if (dish.getCalories() <= 400) return CaloricLevel.DIET;
						else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
						else return CaloricLevel.FAT; },
						toCollection(HashSet::new) 
						)
						));
		System.out.println("caloricLevelsByType2 : " + caloricLevelsByType2);
		
		
		/** 分区： 由一个谓词（返回一个布尔值的函数）作为分类函数，它称为分区函数。分区函数返回一个布尔值，这意味着得到的分组Map的键类型是Boolean  */
		Map<Boolean, List<Dish>> parttion1 = menu.stream()
				.collect(partitioningBy(Dish::isVegetarian));
		System.out.println("分区 ： parttion1  "+parttion1);
		
		Map<Boolean, Map<Type, List<Dish>>> parttion2 = menu.stream()
				.collect(partitioningBy(Dish::isVegetarian, // 分区函数
							groupingBy(Dish::getType))
						);	// 收集器
		System.out.println("分区 并且分组： parttion2  "+parttion2);
		
		// 找到素食和非素食中热量最高的菜
		Map<Boolean, Dish> parttion3 = menu.stream()
		.collect(
				partitioningBy(Dish::isVegetarian, 
						collectingAndThen(maxBy(Comparator.comparing(Dish::getCalories)),
								Optional::get)
						)
			);
		System.out.println(" parttion3  ：" + parttion3);
		
		// 自己定义的toListCollector
		// ToListCollector必须用new来实例化
		List<Dish> toListCollector = menu.stream().collect(new ToListCollector<Dish>());
		System.out.println("自己定义的toListCollector : " + toListCollector);
		
		
		/** 获取质数: */
		// 使用partitioningBy 
		long t1 = System.currentTimeMillis();
		Map<Boolean, List<Integer>> partitionPrimes = partitionPrimes(100);
		long t2 = System.currentTimeMillis();
		System.out.println("测试质数 1 ： " + partitionPrimes + " time1:" + (t2 - t1));
//		System.out.println("time1:" + (t2 - t1));
		long t3 = System.currentTimeMillis();
		// 自定义 收集器  PrimNumbersCollector
		Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector = partitionPrimesWithCustomCollector(100);
		long t4 = System.currentTimeMillis();
		System.out.println("测试质数 2 ： " + partitionPrimesWithCustomCollector + " time1:" + (t4 - t3));
//		System.out.println("time1:" + (t4 - t3));
		Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector2 =partitionPrimesWithCustomCollector2(100);
		System.out.println("测试质数 3 ： " + partitionPrimesWithCustomCollector2);
		
	}
	
	/**
	 * 获取1到n之间的质数与非质数<br>
	 * (使用partitioningBy)
	 * @param n
	 * @return
	 */
	public static Map<Boolean, List<Integer>> partitionPrimes(int n) {
		return IntStream.rangeClosed(2, n)
				.boxed()
				.collect(partitioningBy(candidate -> isPrime(candidate)));
	}

	public static boolean isPrime(int candidate) {
		int candidateRoot = (int) Math.sqrt((double) candidate);
		return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);
	}
	
	
	/**
	 * 获取1到n之间的质数与非质数<br>
	 * (使用自定义收集器)
	 * @param n
	 * @return
	 */
	public static Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector(int n) {
		return IntStream.rangeClosed(2, n).boxed().collect(new PrimNumbersCollector());
	}
	
	/**
	 * 获取1到n之间的质数与非质数(使用自定义收集器)
	 * 使用collect重载的方法,把实现PrimNumbersCollector核心逻辑的三个函数传递给collect当做参数
	 * 避免为实现Collector接口创建一个全新的类
	 * @param n
	 * @return
	 */
	public static Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector2(int n) {
		return IntStream.rangeClosed(2, n)
				.boxed()
				.collect(() -> new HashMap<Boolean, List<Integer>>() {
							{ // 供应源
								put(true, new ArrayList<Integer>());
								put(false, new ArrayList<Integer>());
							}
						}, (acc, candidate) -> { // 累加器
							acc.get(isPrime(acc.get(true), candidate)).add(candidate);
						}, (map1, map2) -> { // 组合器
							map1.get(true).addAll(map2.get(true));
							map1.get(false).addAll(map2.get(false));
						}
					);
	}
	
	/** 判断candidate是否为质数，primes为被测试数之前的质数*/
	public static boolean isPrime(List<Integer> primes, int candidate) {
		int candidateRoot = (int) Math.sqrt((double) candidate);
		return takeWhile(primes, i -> i <= candidateRoot)
				.stream()
				.noneMatch(p -> candidate % p == 0);
	}

	/** 用于优化isPrime方法，只用不大于被测数平方根的质数去测试 */
	public static <A> List<A> takeWhile(List<A> list, Predicate<A> p) {
		int i = 0;
		for (A item : list) {
			if (!p.test(item)) { // 检查列表中的元素是否符合谓词
				return list.subList(0, i); // 如果不符合，截断list
			}
			i++;
		}
		return list;  // 返回List
	}
	
}
