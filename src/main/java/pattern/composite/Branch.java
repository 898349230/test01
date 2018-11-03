package pattern.composite;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Administrator
 *
 */
public class Branch extends Corp {

	List<Corp> subordinateList = new ArrayList<>();
	
	public Branch(String name, String position, int salary) {
		super(name, position, salary);
	}

	/**
	 * 添加下级员工（子节点）
	 */
	public void addSubordinate(Corp corp){
		//设置父节点
		corp.setParent(this);
		subordinateList.add(corp);
	}
	/**
	 * 获取下级员工list
	 * @return
	 */
	public List<Corp> getSubordinate(){
		return this.subordinateList;
	}
}
