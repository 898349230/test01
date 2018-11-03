package java8.stream2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * 开发自己的ToListCollector
 * @param <T>
 */
public class ToListCollector<T> implements Collector<T, List<T>, List<T>>{

	/**
	 * 创建集合操作的起始点
	 */
	@Override
	public Supplier<List<T>> supplier() {
		return ArrayList::new;
	}

	/**
	 * 累计遍历过的项目，原位修改累加器
	 */
	@Override
	public BiConsumer<List<T>, T> accumulator() {
		return List::add;
	}

	/**
	 * 
	 */
	@Override
	public BinaryOperator<List<T>> combiner() {
		return (list1, list2) -> { // 修改第一个累加器，将其与第二个累加器的内容合并
			list1.addAll(list2);
			return list1; 	// 返回修改后的第一个累加器
		};
	}

	/**
	 * 恒等函数
	 */
	@Override
	public Function<List<T>, List<T>> finisher() {
		return Function.identity();
	}

	@Override
	public Set<Characteristics> characteristics() {
		return Collections.unmodifiableSet(EnumSet.of(// 为收集器添加IDENTITY_FINISH和CONCURRENTḽᘍ
				Characteristics.IDENTITY_FINISH, Characteristics.CONCURRENT));
	}

}
