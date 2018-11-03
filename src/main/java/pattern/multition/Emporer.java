package pattern.multition;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Emporer {
	//最大实例数量
	private static int maxNumOfEmporer = 2;
	//存放实例信息
	private static List<String> emporerInfoList = new ArrayList<String>(maxNumOfEmporer);
	//存放实例对象
	private static List<Emporer> emporerList = new ArrayList<Emporer>(maxNumOfEmporer);
	//当前实例的编号
	private static int countNumOfEmporer = 0;
	//加载时候实例化一定数量的对象放入到集合中
	static{
		for(int i=0;i<maxNumOfEmporer;i++){
			emporerList.add(new Emporer("第 "+(i+1)+"	 个皇帝"));
		}
	}
	//私有构造方法
	private Emporer(){
	}
	private Emporer(String info){
		emporerInfoList.add(info);
	}
	//随机获取emporerList中的emporer对象
	public static Emporer getInstance(){
		Random random = new Random();
		countNumOfEmporer = random.nextInt(maxNumOfEmporer);
		return emporerList.get(countNumOfEmporer);
	}
	public static void emporerInfo(){
		System.out.println(emporerInfoList.get(countNumOfEmporer));
	}
}
