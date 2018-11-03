package pattern.strategy;

public class Test {
	public static void main(String[] args) {
		Context con = null;
		//第一个策略
		con = new Context(new FirstStrategy());
		con.operate();
		
		//第二个策略
		con = new Context(new SecondStrategy());
		con.operate();
		
		//第三个策略
		con = new Context(new ThirdStrategy());
		con.operate();
	}
}
