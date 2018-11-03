package pattern.decorate;
/**
 * 装饰角色：持有具体构件类的对象，以便执行原有功能------接口的实现类，类里调用接口的对象 
 * 相当于i/o里面的FilerOutputStream和FilterInputStream。
 * @author Administrator
 *
 */
public abstract class Decorate extends SchoolReport{

	private SchoolReport schoolReport;
	
	public Decorate(SchoolReport schoolReport){
		this.schoolReport = schoolReport;
	}
	@Override
	public void report() {
		this.schoolReport.report();
	}

	@Override
	public void sign(String name) {
		this.schoolReport.sign(name);
	}

}
