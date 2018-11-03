package pattern.decorate;
/**
 * 具体装饰：具体扩展的功能在这里 
 * 相当于i/o流里面的BufferedOutputStream和BufferedInputStream以及DataOutputStream和DataInputSrtream。
 * @author Administrator
 *
 */
public class HighScoreDecorate extends Decorate{

	public HighScoreDecorate(SchoolReport schoolReport) {
		super(schoolReport);
	}

	private void reportHighScore(){
		System.out.println("得了很高分数");
	}
	
	@Override
	public void report() {
		this.reportHighScore();
		super.report();
	}
}
