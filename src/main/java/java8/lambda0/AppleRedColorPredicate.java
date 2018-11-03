package java8.lambda0;

public class AppleRedColorPredicate implements ApplePredicate{

	@Override
	public boolean test(Apple apple) {
		if (apple.getColor().equals("red")) {
			return true;
		}
		return false;
	}

}
