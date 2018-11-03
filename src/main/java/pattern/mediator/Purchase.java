package pattern.mediator;
/**
 * 采购类
 * @author Administrator
 *
 */
public class Purchase extends AbstractColleage{

	public Purchase(AbstractMediator mediator){
		super(mediator);
	}
	//采购电脑
	public void buyComputer(int num){
		super.mediator.execute("purchase.buy", num);
	}
	//不在采购电脑
	public void refuseBuyComputer(){
		System.out.println("不再采购电脑");
	}
	
}
