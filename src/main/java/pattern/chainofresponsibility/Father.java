package pattern.chainofresponsibility;

public class Father extends Hander {

	public Father() {
		super(1);
	}

	@Override
	public void response(IWoman woman) {
		System.out.println("向父亲请示");
		System.out.println(woman.getRequest());
		System.out.println("父亲同意了");
	}

}
