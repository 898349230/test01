package pattern.mediator;
/**
 * 中介者实现类
 * 具体中介者角色通过协调各同事角色实现协作行为，因此它必须依赖于各个同事角色。
 * @author Administrator
 *
 */
public class Mediator extends AbstractMediator{

	//中介者最重要的方法
	@Override
	public void execute(String str, Object... obj) {
		if(str.equals("purchase.buy")){
			this.buyComputer((int) obj[0]);
		}else if(str.equals("sale.sell")){
			this.saleComputer((int) obj[0]);
		}else if(str.equals("sale.offSale")){
			this.offSale();
		}else if(str.equals("clearStock")){
			this.clearSale();
		}
	}
	//采购电脑
	private void buyComputer(int num){
		int status = super.stock.getStockNum();
		if(status>80){//销售状况良好
			System.out.println("采购电脑\t"+num+"\t台");
			super.stock.increase(num);
		}else{//销售状况不好,折半采购
			num = num/2;
			System.out.println("采购电脑\t"+num+"\t台");
			super.stock.decrease(num);
		}
	}
	//销售电脑
	private void saleComputer(int num){
		if(super.stock.getStockNum()<num){//库存不够，
			super.purchase.buyComputer(num);
		}
	//	super.sale.sellComputer(num);
		super.stock.decrease(num);
	}
	//打折出售
	private void offSale(){
		System.out.println("打折销售\t"+super.stock.getStockNum()+"\t台电脑");
	}
	//清仓处理
	private void clearSale(){
		//要求清仓销售
		super.sale.offSale();
		//要求采购不在进行采购
		super.purchase.refuseBuyComputer();
	}
}
