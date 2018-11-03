package pattern.observer;

import java.util.Observable;
import java.util.Observer;
/**
 * 观察者 李斯
 * @author Administrator
 *
 */
public class Lisi implements Observer{

	@Override
	public void update(Observable observable, Object obj) {
		System.out.println("李斯发现韩非子活动了，准备报告老板");
		this.reportToBoss(obj.toString());
		System.out.println("李斯汇报完毕");
	}
	private void reportToBoss(String reportContext){
		System.out.println("李斯报告老板，韩非子有活动了"+reportContext);
	}
}
