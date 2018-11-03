package pattern.template;

public class Car1 extends Car{
	
	private boolean isAlerm = true;
	
	@Override
	protected void start() {
		System.out.println("车1 启动了。。。。。。");
	}

	@Override
	protected void alerm() {
		System.out.println("车1 响喇叭了。。。。。。");
	}

	@Override
	protected void stop() {
		System.out.println("车1 停车了。。。。。。。");
	}
	
	//默认是响喇叭的
	@Override
	protected boolean isAlerm() {
		return this.isAlerm;
	}
	
	public void setAlerm(boolean isAlerm){
		this.isAlerm = isAlerm;
	}

}
