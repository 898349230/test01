package pattern.template;

public abstract class Car {
	
	protected abstract void start();
	protected abstract void alerm();
	protected abstract void stop();
	
	final void run(){
		
		this.start();
		if(isAlerm()){
			this.alerm();
		}
		this.stop();
	}
	
	//钩子方法
	//默认是  响喇叭的
	protected boolean isAlerm(){
		
		return true;
	}
}
