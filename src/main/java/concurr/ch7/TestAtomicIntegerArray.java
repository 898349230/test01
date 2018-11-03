package concurr.ch7;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class TestAtomicIntegerArray {

	/**
	 * AtomicIntegerArray：原子更新整型数组里的元素。
	 * AtomicLongArray：原子更新长整型数组里的元素。
	 * AtomicReferenceArray：原子更新引用类型数组里的元素
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		AtomicIntegerArray intArr = new AtomicIntegerArray(new int[] {1,2,3,4,5});
		System.out.println(intArr);
		
//		以原子方式将输入值与数组中索引i的元素相加。
		int i = intArr.addAndGet(0, 5);
		System.out.println(intArr + " " + i);
		boolean compareAndSet1 = intArr.compareAndSet(1, 2, 5);
		System.out.println("compareAndSet1 " + compareAndSet1 + " intArr" + intArr);

		boolean compareAndSet2 = intArr.compareAndSet(1, 1, 5);
		System.out.println("compareAndSet2 " + compareAndSet2 + " intArr" + intArr);
	
		int get = intArr.getAndSet(4, 10);
		System.out.println("get = " + get + "  intArr" + intArr);
	}
	
	
}
