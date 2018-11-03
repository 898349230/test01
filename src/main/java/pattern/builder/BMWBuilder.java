package pattern.builder;

import java.util.List;

public class BMWBuilder extends CarBuilder {
	BMWModel bmw = new BMWModel();
	@Override
	protected void setSequence(List<String> sequence) {
		this.bmw.setSequence(sequence);
	}

	@Override
	protected CarModel getCarModel() {
		return this.bmw;
	}
}
