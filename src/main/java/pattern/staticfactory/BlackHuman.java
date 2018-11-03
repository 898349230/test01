package pattern.staticfactory;

public class BlackHuman implements Human{

	@Override
	public void laugh() {
		System.out.println("黑人笑");
	}

	@Override
	public void run() {
		System.out.println("黑人跑");		
	}

	@Override
	public void eat() {
		System.out.println("黑人吃饭");
	}

}
