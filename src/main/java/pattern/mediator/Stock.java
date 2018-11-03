package pattern.mediator;
/**
 * 库存
 * @author Administrator
 *
 */
public class Stock extends AbstractColleage{

	public Stock(AbstractMediator mediator) {
		super(mediator);
	}
	
	//刚开始有100台电脑
	private static int COMPUTER_COUNT =100;
	
	//库存增加
	public void increase(int num){
		COMPUTER_COUNT += num;
		System.out.println("库存数量为：\t"+COMPUTER_COUNT);
	}
	//库存减少
	public void decrease(int num){
		COMPUTER_COUNT -= num;
		System.out.println("库存数量为：\t"+COMPUTER_COUNT);
	}
	// 获取库存数量
	public int getStockNum(){
		return COMPUTER_COUNT;
	}
	//库存压力大，通知采购人员不要采购，销售人员尽快销售
	public void clearStock(){
		System.out.println("清理库存数量为：\t"+COMPUTER_COUNT);
		super.mediator.execute("clearStock");
	}
}
