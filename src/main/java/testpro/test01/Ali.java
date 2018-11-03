package testpro.test01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Ali {

	public static void main(String[] args) {
		List<String> list1 = new ArrayList<String>();
		list1.add("1");
		list1.add("2");
		
		// 阿里规范list转为数组
		String[] array = new String[list1.size()];
		array = list1.toArray(array);
		
		System.out.println(list1.toString());
		// 不要在 foreach 中执行 remove，add操作，有可能java.util.ConcurrentModificationException 异常
//		需要的话使用 iterator
//		for (String item : list1) {
//			if("2".equals(item)) {
//				list1.remove(item);
//			}
//		}
		
		// 使用iterator删除
		Iterator<String> iterator = list1.iterator();
		while(iterator.hasNext()) {
			String item = iterator.next();
			if("2".equals(item)) {
				iterator.remove();
			}
		}
		System.out.println(list1.toString());
		
		String[] strArr = new String[]{"a", "b", "c"};
		List<String> asList = Arrays.asList(strArr);
		// 报错 java.lang.UnsupportedOperationException  asList返回的是Arrays的内部类，没有实现集合类的修改方法
//		asList.add("d"); 
		
		System.out.println("修改前 ：" + asList.get(0));
		// 修改strArr的值会改变asList的值
		strArr[0] = "aa";
		System.out.println("修改后 ：" + asList.get(0));
		
	}
}
