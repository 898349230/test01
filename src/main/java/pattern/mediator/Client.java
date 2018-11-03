package pattern.mediator;

/**
 * 场景类
 * @author Administrator
 *
 */
public class Client {
	public static void main(String[] args) {
		AbstractMediator mediator = new Mediator();
		//采购人员采购电脑
		System.out.println("-----------采购申请采购---------------");
		Purchase purchase = new Purchase(mediator);
		purchase.buyComputer(70);
		
		//销售人员销售电脑
		System.out.println("-----------销售销售电脑---------------");
		Sale sale = new Sale(mediator);
		sale.sellComputer(60);
		
		//库房管理人员管理库存
		System.out.println("-----------库房管理人员清库处理---------------");
		Stock stock = new Stock(mediator);
		stock.clearStock();
	}
}
