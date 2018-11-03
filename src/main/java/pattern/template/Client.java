package pattern.template;

public class Client {
	
	public static void main(String[] args) {
		Car1 car1 = new Car1();
		//设置car1 不响喇叭
		car1.setAlerm(false);
		car1.run();
		
		System.out.println("=======================");
		
		Car2 car2 = new Car2();
		car2.run();
	}
}
