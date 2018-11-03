package concurr.ch7;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicStampedReference;

public class TestAtomicIntegerFieldUpdater {

	/**
	 * 原子更新字段类
	 * 
	 * AtomicIntegerFieldUpdater：原子更新整型的字段的更新器
	 * AtomicLongFieldUpdater：原子更新长整型字段的更新器
	 * AtomicStampedReference：原子更新带有版本号的引用类型。该类将整数值与引用关联起来，
	 * 可用于原子的更新数据和数据的版本号，可以解决使用CAS进行原子更新时可能出现的ABA问题。
	 * 
	 * @param args
	 */
	
	// 创建原子更新器，并设置需要更新的对象类和对象的属性
	private static AtomicIntegerFieldUpdater<User> fieldUpdate = AtomicIntegerFieldUpdater.newUpdater(User.class, "age");
	
	static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<Integer>(0, 0); 

	
	public static void main(String[] args) throws InterruptedException {
		testAtomicStampedReference();
	}
	
	static void testAtomicIntegerFieldUpdater() {
		
//		要想原子地更新字段类需要两步。第一步，因为原子更新字段类都是抽象类，每次使用的
//		时候必须使用静态方法newUpdater()创建一个更新器，并且需要设置想要更新的类和属性。第
//		二步，更新类的字段（属性）必须使用public volatile修饰符。
		User u1 = new User("zhangsan", 15);
		fieldUpdate.incrementAndGet(u1);
		System.out.println("incrementAndGet后    " + u1.getAge());
		
	}
	
	/**
	 * AtomicStampedReference：原子更新带有版本号的引用类型。该类将整数值与引用关联起来，
	 * 可用于原子的更新数据和数据的版本号，可以解决使用CAS进行原子更新时可能出现的ABA问题。
	 * {@link} http://blog.csdn.net/xiaoxufox/article/details/51312354
	 * @throws InterruptedException
	 */
	static void testAtomicStampedReference() throws InterruptedException {
		
		final int stamp = atomicStampedReference.getStamp();  
        final Integer reference = atomicStampedReference.getReference();  
		
		Thread t1 = new Thread(new Runnable() {  
            @Override  
            public void run() {  
                    System.out.println(Thread.currentThread() + "-" + reference + "-" + stamp + "-"  
                            + atomicStampedReference.compareAndSet(reference, reference + 10, stamp, stamp + 1));  
            }  
        });  
          
        Thread t2 = new Thread(new Runnable() {  
            @Override  
            public void run() {  
                    Integer reference = atomicStampedReference.getReference();  
                    System.out.println(Thread.currentThread() + "-" + reference + "-" + stamp + "-"  
                            + atomicStampedReference.compareAndSet(reference, reference + 10, stamp, stamp + 1));  
            }  
        });  
          
        t1.start();  
        t2.start();  
        t1.join();  
        t2.join();  
          
        System.out.println(atomicStampedReference.getReference());  
        System.out.println(atomicStampedReference.getStamp());  
	}
	
	
	static class User{
		
		private String name;
//		更新类的字段（属性）必须使用public volatile修饰符。
		public volatile int age;
		
		public User(String name, int age) {
			super();
			this.name = name;
			this.age = age;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		
	}
}
