package pattern.vistor;

/**
 * 访问者实现类
 * 
 * 这个类实现了两个功能（打印报表和计算工资，也可以分开成两个vistor）
 * @author Administrator
 *
 */
public class Visor implements IVistor {
	
	//部门经理的工资系数是5
	private final static int MANAGER_COEFFICIENT = 5;
	//员工的工资系数是2
	private final static int COMMONEMPLOYEE_COEFFICIENT = 2;
	//普通员工工资总和
	private int comonTotalSalary = 0;
	//经理工资总和
	private int managerTotalSalary = 0;
	
	@Override
	public void visit(CommonEmpoyee commonEmpoyee) {
		System.out.println(this.getCommonInfo(commonEmpoyee));
		this.calCommonSalary(commonEmpoyee.getSalary());
	}

	@Override
	public void visit(ManagerEmpoyee manager) {
		System.out.println(this.getManagerInfo(manager));
		this.calManagerSalary(manager.getSalary());
	}
	
	private String getBasicInfo(Employee employee){
		String info = "姓名：" + employee.getName() + "\t";
		info = info + "性别：" + (employee.getSex() == Employee.FEMALE?"女":"男") + "\t";
		info = info + "薪水：" + employee.getSalary() + "\t";
		return info;
	}
	
	private String getManagerInfo(ManagerEmpoyee manager){
		String info = this.getBasicInfo(manager);
		info += "经理的业绩："+ manager.getPerformance()+"\t";
		return info;
	}
	private String getCommonInfo(CommonEmpoyee commonEmpoyee){
		String info = this.getBasicInfo(commonEmpoyee);
		info += "普通员工的工作："+ commonEmpoyee.getJob()+"\t";
		return info;
	}
	/**
	 * 计算普通员工的总和
	 * @param salary
	 */
	private void calCommonSalary(int salary){
		this.comonTotalSalary =this.comonTotalSalary + salary*COMMONEMPLOYEE_COEFFICIENT;
	}
	/**
	 * 计算经理工资总和
	 * @param salary
	 */
	private void calManagerSalary(int salary){
		this.managerTotalSalary = this.managerTotalSalary + salary*MANAGER_COEFFICIENT;
	}
	/**
	 * 获得所有员工的工资总和
	 */
	@Override
	public int getToalSalary() {
		int sum = this.comonTotalSalary+this.managerTotalSalary;
		return sum;
	}
}
