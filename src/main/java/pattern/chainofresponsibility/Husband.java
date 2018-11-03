package pattern.chainofresponsibility;

public class Husband extends Hander {

	public Husband() {
		super(2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void response(IWoman woman) {
		
		System.out.println("向丈夫请示");
		System.out.println(woman.getRequest());
		System.out.println("丈夫同意了");
		
	}

}
