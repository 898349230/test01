package pattern.mediator;

import java.util.Random;

/**
 * 销售
 * @author Administrator
 *
 */
public class Sale extends AbstractColleage{

	public Sale(AbstractMediator mediator) {
		super(mediator);
	}
	//卖电脑
	public void sellComputer(int num){
		super.mediator.execute("sale.sell", num);
		System.out.println("卖出了"+num+"台电脑");
	}
	// 反馈销售情况，0--100之间变化，数值越高 ，卖的越好
	public int getSaleStatus(){
		Random random = new Random();
		int status = random.nextInt(100);
		System.out.println("电脑的销售状态是：\t"+status);
		return status;
	}
	//打折销售
	public void offSale(){
		super.mediator.execute("sale.offSale");
	}
	
}
