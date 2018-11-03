package pattern.template;

public class Car2 extends Car{
	
	@Override
	protected void start() {
		System.out.println("车2 启动了。。。。。。。。");
	}

	@Override
	protected void alerm() {
		System.out.println("车2 响喇叭了 。。。。。。");
	}

	@Override
	protected void stop() {
		System.out.println("车2 停车了。。。。。。。。");
	}

	@Override
	protected boolean isAlerm() {
		return false;
	}
	
	
	
}
