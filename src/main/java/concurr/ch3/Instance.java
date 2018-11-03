package concurr.ch3;

public class Instance {
	static {
		System.out.println(" static Instance ");
	}
	public Instance() {
		System.out.println("constructor Instance");
	}
}
