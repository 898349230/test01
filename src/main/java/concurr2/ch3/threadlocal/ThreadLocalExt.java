package concurr2.ch3.threadlocal;

import java.util.Date;

public class ThreadLocalExt extends ThreadLocal{

	/**
	 * 设置ThreadLocal的初始值
	 */
	@Override
	protected Object initialValue() {
		long ret =  new Date().getTime();
//		return " 默认的初始值";
		return ret;
	}
	
}
