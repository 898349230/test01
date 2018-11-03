package com.ab.cloneable;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试 深克隆
 * @author Administrator
 *
 */
public class Thing3 implements Cloneable{
	
	private ArrayList<String> list = new ArrayList<String>();

	@Override
	protected Thing3 clone() {
		Thing3 t =null;
		try {
			t = (Thing3) super.clone();
			t.list = (ArrayList<String>) this.list.clone();
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
