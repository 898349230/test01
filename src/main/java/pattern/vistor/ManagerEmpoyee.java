package pattern.vistor;

public class ManagerEmpoyee extends Employee{

	private String performance;
	
	public String getPerformance() {
		return performance;
	}

	public void setPerformance(String performance) {
		this.performance = performance;
	}

	@Override
	public void accept(IVistor vistor) {
		vistor.visit(this);
	}
}
