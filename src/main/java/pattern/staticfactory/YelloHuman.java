package pattern.staticfactory;

public class YelloHuman implements Human {

	@Override
	public void laugh() {
		System.out.println("黄种人大笑");
	}

	@Override
	public void run() {
		System.out.println("黄种人跑的快");
	}

	@Override
	public void eat() {
		System.out.println("黄种人吃饭");
	}

}
