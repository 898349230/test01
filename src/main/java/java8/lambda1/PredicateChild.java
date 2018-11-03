package java8.lambda1;

import java.util.function.Predicate;

public class PredicateChild<T> implements Predicate<T>{

	@Override
	public boolean test(T t) {
		return false;
	}

}
