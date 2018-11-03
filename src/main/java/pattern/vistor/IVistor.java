package pattern.vistor;

/**
 * 访问者  访问别人家的数据
 * @author Administrator
 *
 */
public interface IVistor {
	//访问普通员工
	public void visit(CommonEmpoyee commonEmpoyee);
	//访问经理
	public void visit(ManagerEmpoyee manager);
	//统计总工资
	public int getToalSalary();
}
