package pattern.composite;
/**
 * 定义一个公司人员的抽象类
 * @author Administrator
 *
 */
public abstract class Corp {
	private String name;
	private String position;
	private int salary;
	//上一级节点
	private Corp parent;
	
	public Corp(String name, String position, int salary) {
		super();
		this.name = name;
		this.position = position;
		this.salary = salary;
	}
	
	public String getInfo(){
		return "\t姓名 ："+this.name+"\t职位 ："+this.position+"\t薪水 ："+this.salary;
	}
	
	public Corp getParent() {
		return parent;
	}
	//设置父级节点
	protected void setParent(Corp parent) {
		this.parent = parent;
	}
	
	
}
