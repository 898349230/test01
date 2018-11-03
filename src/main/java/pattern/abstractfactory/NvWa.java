package pattern.abstractfactory;

public class NvWa {
	public static void main(String[] args) {
		MaleHumanFactory factory = new MaleHumanFactory();
		Human blackHuman = factory.createBlackHuman();
		blackHuman.eat();
		
		FemaleHumanFactory fFactory = new FemaleHumanFactory();
		Human yellowHuman = fFactory.createYellowHuman();
		yellowHuman.eat();
	}
}
