package pattern.bridge;

public class IPod extends Product {

	@Override
	protected void beProduced() {
		System.out.println("生产了   IPod");
	}

	@Override
	protected void beSelled() {
		System.out.println("卖出了   IPod");

	}

}
