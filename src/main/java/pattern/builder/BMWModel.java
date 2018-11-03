package pattern.builder;
/**
 * product class
 * 产品类（实现了模板方法模式）
 * @author Administrator
 *
 */
public class BMWModel extends CarModel {

	@Override
	protected void start() {
		System.out.println("BMW start.......");
	}

	@Override
	protected void alarm() {
		System.out.println("BMW alarm.......");
	}

	@Override
	protected void boom() {
		System.out.println("BMW boom.......");
	}

	@Override
	protected void stop() {
		System.out.println("BMW stop.......");
	}

}
