package pattern.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * 导演类   安排已有模块的顺序，然后调用builder进行建造model
 * @author Administrator
 *
 */
public class Director {
	
	private List<String> sequence = new ArrayList<String>();
	private CarBuilder bmwBuilder = new BMWBuilder();
	private CarBuilder benziBuilder = new BenziBuilder();
	
	public CarModel getABenciModel(){
		System.out.println("奔驰A  ：  ");
		//清理场景  防止数据混乱
		this.sequence.clear();
		this.sequence.add("start");
		this.sequence.add("stop");
		benziBuilder.setSequence(sequence);
		return benziBuilder.getCarModel();
	}
	public CarModel getBBenciModel(){
		System.out.println("奔驰B  ：  ");
		this.sequence.clear();
		this.sequence.add("start");
		this.sequence.add("alarm");
		this.sequence.add("stop");
		benziBuilder.setSequence(sequence);
		return benziBuilder.getCarModel();
		
	}
	public CarModel getABMWModel(){
		System.out.println("宝马A  ：  ");
		this.sequence.clear();
		this.sequence.add("start");
		this.sequence.add("stop");
		bmwBuilder.setSequence(sequence);
		return bmwBuilder.getCarModel();
	}
	public CarModel getBBMWModel(){
		System.out.println("宝马B  ：  ");
		this.sequence.clear();
		this.sequence.add("start");
		this.sequence.add("boom");
		this.sequence.add("stop");
		bmwBuilder.setSequence(sequence);
		return bmwBuilder.getCarModel();
	}
}
