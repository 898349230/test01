package pattern.mediator;

public abstract class AbstractColleage {
	protected AbstractMediator mediator;

	public AbstractColleage(AbstractMediator mediator) {
		this.mediator = mediator;
	}
	
}
