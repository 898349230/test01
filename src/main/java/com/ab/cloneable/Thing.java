package com.ab.cloneable;

/**
 * 测试对象拷贝时，类的构造函数是不会被执行的。
 * @author Administrator
 *
 */
public class Thing implements Cloneable{
	public Thing(){
		System.out.println("构造函数执行了。。。");
	}

	@Override
	protected Thing clone(){
		Thing t = null;
		try {
			t = (Thing) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return t;
	}
	
}
