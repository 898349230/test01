package com.ab.cloneable;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试 浅克隆
 * @author Administrator
 *
 */
public class Thing2 implements Cloneable{
	
	private List<String> list = new ArrayList<String>();

	@Override
	protected Thing2 clone() {
		Thing2 t =null;
		try {
			t = (Thing2) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return t;
	}
	//为list 赋值
	public void setValue(String str){
		this.list.add(str);
	}
	// 获取list
	public List getValue(){
		return this.list;
	}
}
