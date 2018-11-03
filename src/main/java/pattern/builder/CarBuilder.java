package pattern.builder;

import java.util.List;

public abstract class CarBuilder {
	protected abstract void setSequence(List<String> sequence);
	protected abstract CarModel getCarModel();
}
