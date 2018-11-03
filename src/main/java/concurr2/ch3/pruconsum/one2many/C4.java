package concurr2.ch3.pruconsum.one2many;

public class C4 {

	private MyStack2 stack ;
	
	public C4(MyStack2 stack) {
		this.stack = stack;
	}
	
	public void popService() {
		stack.pop();
	}
}
