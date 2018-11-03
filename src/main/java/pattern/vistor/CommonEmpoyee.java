package pattern.vistor;

public class CommonEmpoyee extends Employee{

	private String job;
	
	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	@Override
	public void accept(IVistor vistor) {
		vistor.visit(this);
	}
}
