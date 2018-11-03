package pattern.bridge;

public abstract class Corporation {
	private Product product = null;
	public Corporation(Product product){
		this.product = product;
	}
	public void makeMoney(){
		this.product.beProduced();
		this.product.beSelled();
		System.out.println("卖完商品赚钱了。。。。。。");
	}
}
