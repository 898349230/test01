package concurr2.ch3.pruconsum.onetone2;

public class C3 {

	private MyStack stack ;
	
	public C3(MyStack stack) {
		this.stack = stack;
	}
	
	public void popService() {
		stack.pop();
	}
}
