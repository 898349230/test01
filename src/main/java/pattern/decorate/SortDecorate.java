package pattern.decorate;
/**
 * 具体装饰：具体扩展的功能在这里 
 * 相当于i/o流里面的BufferedOutputStream和BufferedInputStream以及DataOutputStream和DataInputSrtream。
 * @author Administrator
 *
 */
public class SortDecorate extends Decorate{

	public SortDecorate(SchoolReport schoolReport) {
		super(schoolReport);
	}
	
	private void reportSort(){
		System.out.println("排名第20");
	}

	@Override
	public void report() {
		super.report();
		this.reportSort();
	}
	
	
}
