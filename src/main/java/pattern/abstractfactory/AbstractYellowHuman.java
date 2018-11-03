package pattern.abstractfactory;

public abstract class AbstractYellowHuman implements Human {

	@Override
	public void laugh() {
		System.out.println("黄色人种笑 ");
	}

	@Override
	public void eat() {
		System.out.println("黄色人种吃饭");
	}

}
