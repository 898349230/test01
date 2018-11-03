package pattern.bridge;

public class House extends Product {

	@Override
	protected void beProduced() {
		System.out.println("建造了   house。。。。。。");
	}

	@Override
	protected void beSelled() {
		System.out.println("卖出了  house.........");
	}

}
