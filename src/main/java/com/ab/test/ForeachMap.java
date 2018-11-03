package com.ab.test;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ForeachMap {
	public static void main(String[] args) {
		int[] intArr = {1,2,3,4,5};
		
		String s = "a"+"b"+"c"+"d";
		//System.out.println(s=="abcd");
		
		//遍历  map
		Map<Integer, Integer> map = new HashMap();
		map.put(1, 11);
		map.put(2, 22);
		map.put(3, 33);
		//第一种遍历方式
		Set<Integer> keySet = map.keySet();
		for (Integer key : keySet) {
			//System.out.println(map.get(key));
		}
		//第二种遍历方式
		Collection<Integer> values = map.values();
		for (Integer value : values) {
			//System.out.println(value);
		}
		//第三种遍历方式
		Set<Entry<Integer,Integer>> entrySet = map.entrySet();
		for (Entry<Integer, Integer> entry : entrySet) {
			System.out.println(entry.getKey()+"\t"+entry.getValue());
		}
		
		
		//遍历set
		Set set = new HashSet();
		set.add(1);
		set.add(2);
		set.add(3);
		set.add(4);
		//第一种遍历
		for (Object obj: set) {
			//System.out.println(obj);
		}
		//第二种遍历
		Iterator iterator = set.iterator();
		while(iterator.hasNext()){
			//System.out.println(iterator.next());
		}
	}
}
