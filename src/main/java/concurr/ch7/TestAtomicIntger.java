package concurr.ch7;

import java.util.concurrent.atomic.AtomicInteger;

public class TestAtomicIntger {

	/**
	 * AtomicBoolean：原子更新布尔类型
	 * AtomicInteger：原子更新整型
	 * AtomicLong：原子更新长整型 
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
	
		AtomicInteger i = new  AtomicInteger(1);
//		以原子方式将输入的数值与实例中的值（AtomicInteger里的value）相加，并返回结果。
		i.addAndGet(2);
		System.out.println(i);
		
//		如果输入的数值等于预期值，则以原子方式将该值设置为输入的值
		boolean compareAndSet1 = i.compareAndSet(1, 4);
		System.out.println("compareAndSet1 " + compareAndSet1 + "  " + i);
		boolean compareAndSet2 = i.compareAndSet(3, 4);
		System.out.println("compareAndSet2 " + compareAndSet2 + "  " + i);
		
//		以原子方式将当前值加1，注意，这里返回的是自增前的值。
		int ii = i.getAndIncrement();
		System.out.println("getAndIncrement i = " + i + " ii = " + ii);
		
//		最终会设置成newValue，使用lazySet设置值后，可能导致其他线程在之后的一小段时间内还是可以读到旧的值
//		
		i.lazySet(10);
		System.out.println("lazySet " + i);
		
//		以原子方式设置为newValue的值，并返回旧值
		int oldValue = i.getAndSet(12);
		System.out.println("getAndSet i = " + i + " oldValue = " + oldValue);
		
	}
	
	
}
