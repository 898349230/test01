package pattern.bridge;

public class Clothes extends Product {

	@Override
	protected void beProduced() {
		System.out.println("生产了  衣服。。。。。");
	}

	@Override
	protected void beSelled() {
		System.out.println("卖出了 衣服。。。。。");
	}

}
