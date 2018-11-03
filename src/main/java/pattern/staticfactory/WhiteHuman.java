package pattern.staticfactory;

public class WhiteHuman implements Human {

	@Override
	public void laugh() {
		System.out.println("白人笑");
	}

	@Override
	public void run() {
		System.out.println("白人跑");
	}

	@Override
	public void eat() {
		System.out.println("白人吃剩牛排哦");
	}

}
