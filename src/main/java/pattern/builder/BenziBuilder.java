package pattern.builder;

import java.util.List;
/**
 * Builder class 
 * 建造者  (负责建造model)
 * @author Administrator
 *
 */
public class BenziBuilder extends CarBuilder {
	BenziModel benzi = new BenziModel();
	@Override
	protected void setSequence(List<String> sequence) {
		this.benzi.setSequence(sequence);
	}

	@Override
	protected CarModel getCarModel() {
		return this.benzi;
	}

}
