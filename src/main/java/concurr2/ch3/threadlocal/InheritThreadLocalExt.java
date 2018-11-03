package concurr2.ch3.threadlocal;

import java.util.Date;

/**
 * 使用InheritableThreadLocal可以让子线程从父线程哪里获取值
 * @author 
 *
 */
public class InheritThreadLocalExt extends InheritableThreadLocal{

	@Override
	protected Object initialValue() {
		return new Date().getTime();
	}
	
}
