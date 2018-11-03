package pattern.builder;

import java.util.List;

public abstract class CarModel {
	
	private List<String> sequence = null;

	protected abstract void start();

	protected abstract void alarm();

	protected abstract void boom();

	protected abstract void stop();

	public void run() {
		for (int i = 0; i < this.sequence.size(); i++) {
			if (this.sequence.get(i).equalsIgnoreCase("start")) {
				this.start();
			} else if (this.sequence.get(i).equalsIgnoreCase("alarm")) {
				this.alarm();
			} else if (this.sequence.get(i).equalsIgnoreCase("boom")) {
				this.boom();
			} else if (this.sequence.get(i).equalsIgnoreCase("stop")) {
				this.stop();
			}
		}
	}

	public void setSequence(List<String> sequence) {
		this.sequence = sequence;
	}
}
