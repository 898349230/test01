package concurr.ch7;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;


public class TestAtomicReference {

	static AtomicReference<User> atomicUserRef = new AtomicReference<>() ;
	
	static AtomicReferenceFieldUpdater<User, String> atomicReferenceFieldUpdater = AtomicReferenceFieldUpdater.newUpdater(User.class, String.class, "name");
	/**
	 * 原子更新引用类型
	 * 
	 * AtomicReference：原子更新引用类型
	 * AtomicReferenceFieldUpdater：原子更新引用类型里的字段
	 * AtomicMarkableReference：原子更新带有标记位的引用类型。可以原子更新一个布尔类型的标记位和引用类型。
	 * 构造方法是AtomicMarkableReference（V initialRef，booleaninitialMark）
	 * 
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		
		testAtomicReference();
	}
	
	static void testAtomicReference() {
		User u1 = new User("A", 15);
		atomicUserRef.set(u1);
		User u2 = new User("B", 25);
		atomicUserRef.compareAndSet(u1, u2);
		User user = atomicUserRef.get();
		System.out.println(user.getName() + "   " +user.getAge());
		
		System.out.println(u1.getName() + "    " + u1.getAge());
//		这里compareAndSet的filed必须是 public volatile修饰的
		atomicReferenceFieldUpdater.compareAndSet(u1, "A", "C");
		System.out.println(u1.getName() + "    " + u1.getAge());
	}
	
	static class User{
		
//		public volatile String name;
		private String name;
		private int age;
		
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
