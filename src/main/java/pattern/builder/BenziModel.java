package pattern.builder;

/**
 * product class
 * 产品类（实现了模板方法模式）
 * @author Administrator
 *
 */
public class BenziModel extends CarModel {

	@Override
	protected void start() {
		System.out.println("Benzi start.......");
	}

	@Override
	protected void alarm() {
		System.out.println("Benzi alarm.......");
	}

	@Override
	protected void boom() {
		System.out.println("Benzi boom.......");
	}

	@Override
	protected void stop() {
		System.out.println("Benzi stop.......");
	}

}
