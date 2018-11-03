package concurr2.ch3.pruconsum.onetone2;

public class P3 {

	private MyStack stack ;
	
	public P3(MyStack stack) {
		this.stack = stack;
	}
	
	public void pushService() {
		stack.push();
	}
}
