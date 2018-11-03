package pattern.chainofresponsibility;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Client {
	public static void main(String[] args) {
		
		Random random = new Random();
		List<IWoman> list = new ArrayList<IWoman>();
		//随机生成 5个不同类型的女性
		for(int i=0; i < 5;i++){
			IWoman woman = new Woman(random.nextInt(4),"出去走走");
			list.add(woman);
		}
		
		Hander father = new Father();
		Hander husband = new Husband();
		Hander son = new Son();
		//设置责任链的顺序
		father.setNextHander(husband);
		husband.setNextHander(son);
		
		for (IWoman iWoman : list) {
			father.HandleMessage(iWoman);
			System.out.println();
		}
		
	}
}
