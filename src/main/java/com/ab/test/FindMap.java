package com.ab.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class FindMap {
	public static void main(String[] args) {
		//FindMap.map1();
		FindMap.findWord();
	}
	/**
	 * 查找map集合中value最大的 键值对的 key;
	 */
	public static void map1(){
		Map<String,Integer> map = new HashMap();
		map.put("key1", 2);
		map.put("key2", 5);
		map.put("key3", 12);
		map.put("key4", 32);
		map.put("key5", 25);
		map.put("key6", 22);
		map.put("key7", 21);
		Set<Entry<String,Integer>> entrySet = map.entrySet();
		Collection<Integer> values = map.values();
		Iterator<Integer> iterator = values.iterator();
		List<Integer> list = new ArrayList();
		while(iterator.hasNext()){
			list.add(iterator.next());
		}
		Collections.sort(list);
		Map<String,Integer> m = new HashMap();
		for (Entry<String, Integer> entry : entrySet) {
			if(entry.getValue()==list.get(list.size()-1)){
				m.put(entry.getKey(), entry.getValue());
				break;
			}
		}
		System.out.println(m.toString());
	}
	/**
	 * 判断一句话中出现某个单词的次数
	 */
	public static void findWord(){
		String str = "a b bc   a   bc  ddd ddd yu bc   ";
		String[] strings = str.split("\\s+");
		Map<String,Integer> map = new HashMap();
		for (String string : strings) {
			if(map.get(string)==null){
				map.put(string, 1);
			}else{
				map.put(string,map.get(string)+1);
			}
		}
		Set<Entry<String,Integer>> entrySet = map.entrySet();
		for (Entry<String, Integer> entry : entrySet) {
			System.out.println("String : "+entry.getKey()+"  出现次数 ： "+entry.getValue());
		}
	}
}
