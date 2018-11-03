package pattern.chainofresponsibility;

public class Woman implements IWoman {

	/**
	 * 使用 int 类型表示妇女的状态
	 * 1  未出嫁
	 * 2  已出嫁
	 * 3   亡夫
	 */
	private int type;
	private String request="";
	
	public Woman(int type, String request) {
		this.type = type;
		this.request = request;
	}

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return this.type;
	}

	@Override
	public String getRequest() {
		// TODO Auto-generated method stub
		return this.request;
	}

}
