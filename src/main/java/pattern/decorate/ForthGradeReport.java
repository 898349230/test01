package pattern.decorate;

/**
 * 具体构件：具体的原始功能类----实现接口的类---------------
 * 相当于i/o里面的FileOutputStream和FileInputStream。
 * @author Administrator
 *
 */
public class ForthGradeReport extends SchoolReport{

	@Override
	public void report() {
		System.out.println("这是成绩单。。。。");
	}

	@Override
	public void sign(String name) {
		System.out.println("签名    ： "+ name);
	}

}
