package pattern.observer;

import java.util.Observable;

/**
 * 被观察者 韩非子
 * @author Administrator
 *
 */
public class HanFeiZi extends Observable{
	
	public void breakFast(){
		System.out.println("韩非子准备吃饭了");
		super.setChanged();
		super.notifyObservers("韩非子在吃饭。。。");
	}
	
	public void haveFun(){
		System.out.println("韩非准备玩。。。");
		super.setChanged();
		super.notifyObservers("韩非子正在玩。。。");
	}
}
