package pattern.abstractfactory;

public abstract class AbstractWhiteHuman implements Human {

	@Override
	public void laugh() {
		System.out.println("白色人种笑 ");
	}

	@Override
	public void eat() {
		System.out.println("白色人种吃饭");
	}

}
