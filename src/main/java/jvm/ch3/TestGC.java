package jvm.ch3;

public class TestGC {

	public static void main(String[] args) {
		ReferenceCountingGC instanceA = new ReferenceCountingGC();
		ReferenceCountingGC instanceB = new ReferenceCountingGC();
		instanceA.instance = instanceB;
		instanceB.instance = instanceA;
		try {
			Thread.sleep(5000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		instanceA = null;
		instanceB = null;
		System.gc();
		
	}
}
