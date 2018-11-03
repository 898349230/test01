package java8.lambda0;

public class AppleHeavyWeightPredicate implements ApplePredicate{

	@Override
	public boolean test(Apple apple) {
		return apple.getWeight() > 150 ;
	}

}
