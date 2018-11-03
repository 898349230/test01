package com.ab.json;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestJson {
	
	/**
	 * pojo --> json
	 */
	@Test
	public void pojo2Json(){
		Contact contact = new Contact("18742518930","北京朝阳","898349230");
		Map map = new HashMap();
		
		map.put(1, "map1");
		map.put("key2", "map2");
		map.put("key3", 3);
		
		Set set = new HashSet();
		set.add("setValue1");
		set.add(2);
		set.add("setValue3");

		List list = new ArrayList();
		list.add(1);
		list.add("list2");
		list.add("list3");

		Date birthday = new Date();
		
		User user = new User("1","Bill",20,1234.6,true,'c',birthday,contact,map,list,set);
		
		System.out.println("User to String : "+user.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			String userJson = mapper.writeValueAsString(user);
			System.out.println("user to json : "+ userJson);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * json --> pojo
	 */
	@Test
	public void json2Pojo(){
		String jsonStr = "{\"id\":\"1\",\"one\":\"c\",\"birthday\":1489023972067,"
							+ "\"contact\":{\"phone\":\"18742518930\"},"
							+ "\"map\":{\"1\":\"map1\",\"key2\":\"map2\"},"
							+ "\"list\":[1,\"list2\"],"
							+ "\"set\":[2,\"setValue1\"]}";
	
		ObjectMapper mapper = new ObjectMapper();
		try {
			User user = mapper.readValue(jsonStr, User.class);
			System.out.println(user.toString());
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	/**
	 *  set list 转换为json数组
	 *  对象  map  转换为键值对形式
	 */
	@Test
	public void list2Json(){
		Contact contact = new Contact("18888888888","天津河西","89999999");
		Contact contact2 = new Contact("1666666666","北京朝阳","55555555");
		Contact contact3 = new Contact("1222222222","河北石家庄","88888888");
		Map mapV = new HashMap();
		
		mapV.put(1, "map1");
		mapV.put("key2", "map2");
		mapV.put("key3", 3);
		
		Set setV = new HashSet();
		setV.add("setValue1");
		setV.add(2);
		setV.add("setValue3");
		
		List listV = new ArrayList();
		listV.add(1);
		listV.add("list2");
		listV.add("list3");
		
		Date birthday = new Date();
		
		User user1 = new User("1","Jack",18,1111.5,true,'a',birthday,contact,mapV,listV,setV);
		User user2 = new User("1","Bill",19,2222.6,true,'b',birthday,contact,mapV,listV,setV);

		List l = new ArrayList();
		l.add(user1);
		l.add(setV);
		l.add(user2);
		l.add(mapV);
		l.add(listV);
		l.add("testList");
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			String jsonStr = mapper.writeValueAsString(l);
			System.out.println(jsonStr);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 不规则 Json 转换为List(ArrayList) 会把json对象转为map集合，json数组转为ArrayList
	 * debug  查看变量发现  map 是以 LinkedHashMap 形式存储的
	 */
	@Test
	public void json2List(){
		
		String jsonStr = "[[2,\"setValue1\",\"setValue3\"],"   //set
				+ "{\"id\":\"1\",\"name\":\"Bill\",\"age\":19,\"pay\":2222.6,\"valid\":true,\"one\":\"b\",\"birthday\":1489027844129,"
						+ "\"contact\":{\"phone\":\"18888888888\",\"address\":\"天津河西\",\"qq\":\"89999999\"}," // user里面的Contact
							+ "\"map\":{\"1\":\"map1\",\"key2\":\"map2\",\"key3\":3}," // contact 中的 map
							+ "\"list\":[1,\"list2\",\"list3\"],"   // contact 中的 list
							+ "\"set\":[2,\"setValue1\",\"setValue3\"]},"  // contact 中的 set
				+ "{\"1\":\"map1\",\"key2\":\"map2\",\"key3\":3},"  // map
				+ "[1,\"list2\",\"list3\"],"  // list
				+ "\"testList\"]";   // string
		ObjectMapper mapper = new ObjectMapper();
		try {
		//	List list = mapper.readValue(jsonStr, List.class);
			ArrayList list = mapper.readValue(jsonStr, ArrayList.class);
		
			// set 变成了Arraylist
			List setList = (List)list.get(0); 
			System.out.println(" setList : "+ setList.get(1));
		
			//User（对象） 变成了 LinkedHashMap
			Map user =(Map)list.get(1);
			System.out.println( "user.name : "+user.get("name")+
								" user.birthday : "+user.get("birthday")+
								" user.contact.phone : "+((Map)user.get("contact")).get("phone")+ // contact对象转换为map
								" user.valid"+ user.get("valid")+
								" user.map[2] "+((Map) user.get("map")).get("key2")+
								" user.list : "+ ((List)user.get("list")).get(1)
								);
			
			// HashMap 变成了 LinkedHashMap
			Map map = (Map) list.get(2);
			System.out.println("map : "+ map.get("key2"));
			
			// ArrayList
			List l = (List) list.get(3);
			System.out.println("list : "+ l.get(2));
			//String
			System.out.println("String : "+list.get(4));
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * json数组 --> 有泛型的list集合
	 */
	@Test
	public void json2List2(){
		// json 中含有3个json对象 
		String jsonStr = "[{\"id\":\"1\",\"name\":\"Bill\",\"age\":19,\"pay\":2222.6,\"valid\":true,\"one\":\"b\",\"birthday\":1489027844129,"
								+ "\"contact\":{\"phone\":\"18888888888\",\"address\":\"天津河西\",\"qq\":\"89999999\"}," 
									+ "\"map\":{\"1\":\"map1\",\"key2\":\"map2\",\"key3\":3}," 
									+ "\"list\":[1,\"list2\",\"list3\"],"   
									+ "\"set\":[2,\"setValue1\",\"setValue3\"]},"
							+"{\"id\":\"2\",\"name\":\"Danny\",\"age\":20,\"pay\":2222.6,\"valid\":true,\"one\":\"b\",\"birthday\":1489027844129,"
								+ "\"contact\":{\"phone\":\"18888888888\",\"address\":\"天津河西\",\"qq\":\"89999999\"},"  
									+ "\"map\":{\"1\":\"map1\",\"key2\":\"map2\",\"key3\":3},"  
									+ "\"list\":[1,\"list2\",\"list3\"],"   
									+ "\"set\":[2,\"setValue1\",\"setValue3\"]},"
							+	"{\"id\":\"3\",\"name\":\"Lucy\",\"age\":22,\"pay\":2222.6,\"valid\":true,\"one\":\"b\",\"birthday\":1489027844129,"
								+ "\"contact\":{\"phone\":\"18888888888\",\"address\":\"天津河西\",\"qq\":\"89999999\"},"  
									+ "\"map\":{\"1\":\"map1\",\"key2\":\"map2\",\"key3\":3}," 
									+ "\"list\":[1,\"list2\",\"list3\"],"   
									+ "\"set\":[2,\"setValue1\",\"setValue3\"]}]";
		
		List<User> list = this.json2ListUtil(jsonStr, User.class);
		for (User user : list) {
			System.out.println(user.getName());
		}
		
	}
	/**
	 * json --> map
	 */
	@Test
	public void json2Map(){
		String jsonStr = "{\"id\":\"1\",\"one\":\"c\",\"birthday\":1489023972067,"
				+ "\"contact\":{\"phone\":\"18742518930\"},"
				+ "\"map\":{\"1\":\"map1\",\"key2\":\"map2\"},"
				+ "\"list\":[1,\"list2\"],"
				+ "\"set\":[2,\"setValue1\"]}";
		ObjectMapper mapper = new ObjectMapper();
		try {
			Map map = mapper.readValue(jsonStr,Map.class);
			Set keySet = map.keySet();
			for (Object object : keySet) {
				System.out.println(map.get(object));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 自定义转换为list 的 泛型
	 * @param jsonStr
	 * @param classType
	 * @return
	 */
	private <T> List<T> json2ListUtil(String jsonStr,Class<T> classType){
		ObjectMapper mapper = new ObjectMapper();
		JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, classType);
		try {
			List<T> list = mapper.readValue(jsonStr, javaType);
			return list;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {

	}
}
