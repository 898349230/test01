package jvm.ch3;

public class ReferenceCountingGC {

	ReferenceCountingGC instance;

	public ReferenceCountingGC getReferenceCountingGC() {
		return instance;
	}

	public void setReferenceCountingGC(ReferenceCountingGC instance) {
		this.instance = instance;
	}
	
}
