package concurr2.ch3.pruconsum.one2many;

public class P4 {

	private MyStack2 stack ;
	
	public P4(MyStack2 stack) {
		this.stack = stack;
	}
	
	public void pushService() {
		stack.push();
	}
}
