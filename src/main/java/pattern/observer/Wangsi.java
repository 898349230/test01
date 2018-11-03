package pattern.observer;

import java.util.Observable;
import java.util.Observer;

public class Wangsi implements Observer{

	@Override
	public void update(Observable observable, Object obj) {
		//System.out.println(observable.toString());
		System.out.println("王思 发现韩非子有活动了");
		this.happy(obj.toString());
		System.out.println("王思 快乐结束了");
	}
	private void happy(String content){
		System.out.println("王思因为"+content+"很快乐");
	}
}
