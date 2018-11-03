package pattern.abstractfactory;

public abstract class AbstractBlackHuman implements Human {

	@Override
	public void laugh() {
		System.out.println("黑色人种笑 ");
	}

	@Override
	public void eat() {
		System.out.println("黑色人种吃饭");
	}

}
