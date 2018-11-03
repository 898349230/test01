package java8.lambda3;

/**
 * 自定义三个参数的方法
 * @author 
 *
 * @param <T>
 * @param <U>
 * @param <V>
 * @param <R>
 */
public interface TriFunction<T, U, V, R> {
	R Apply(T t, U u, V v);
}
