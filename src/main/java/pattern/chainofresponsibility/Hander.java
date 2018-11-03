package pattern.chainofresponsibility;

public abstract class Hander {
	//能处理的级别
	private int level = 0;
	//责任传递，下一个责任人是谁
	private Hander nextHander;
	
	//每个类说明一下自己能处理哪些要求
	public Hander(int level) {
		this.level = level;
	}

	public void setNextHander(Hander nextHander) {
		this.nextHander = nextHander;
	}

	// 一个女性，不同的角色（女儿，妻子，母亲） 提出要求 由不同的人去处理
	public final void HandleMessage(IWoman woman){
		if(woman.getType()==this.level){
			this.response(woman);
		}else if(this.nextHander!=null){
				this.nextHander.HandleMessage(woman);;
		}else{
			System.out.println("没地方请示了，不做处理");
		}
	}
			
	public abstract void response(IWoman woman);
	
}
