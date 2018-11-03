package pattern.vistor;

import java.util.ArrayList;
import java.util.List;

public class Client {
	
	public static void main(String[] args) {
		
		IVistor vistor = new Visor();
		
		List<Employee> list = getEmpoyee();
		for (Employee employee : list) {
			employee.accept(vistor);
		}
		System.out.println("工资总额是："+vistor.getToalSalary());
	}
	
	//模拟出公司的人员情况，我们可以想象这个数据室通过持久层传递过来的
	public static List<Employee> getEmpoyee(){
		List<Employee> empList = new ArrayList<Employee>();
		//产生张三这个员工
		CommonEmpoyee zhangSan = new CommonEmpoyee();
		zhangSan.setJob("编写Java程序，绝对的蓝领、苦工加搬运工");
		zhangSan.setName("张三");
		zhangSan.setSalary(1800);
		zhangSan.setSex(Employee.MALE);
		empList.add(zhangSan);
		//产生李四这个员工
		CommonEmpoyee liSi = new CommonEmpoyee();
		liSi.setJob("页面美工，审美素质太不流行了！");
		liSi.setName("李四");
		liSi.setSalary(1900);
		liSi.setSex(Employee.FEMALE);
		empList.add(liSi);
		//再产生一个经理
		ManagerEmpoyee wangWu = new ManagerEmpoyee();
		wangWu.setName("王五");
		wangWu.setPerformance("基本上是负值，但是我会拍马屁呀");
		wangWu.setSalary(18750);
		wangWu.setSex(Employee.MALE);
		empList.add(wangWu);
		return empList;
	}
}
