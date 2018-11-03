package pattern.interpretor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Client {
	
	public static void main(String[] args) throws IOException {
		//String expStr = getExpStr();
		String expStr = "a+b-c";
		//赋值
		HashMap<String,Integer> var = getValue(expStr);
		Calculator cal = new Calculator(expStr);
		int result = cal.run(var);
		System.out.println(expStr +"="+result);
	}
	//获得表达式
	public static String getExpStr() throws IOException{
		System.out.println("输入表达式");
		String str = new BufferedReader(new InputStreamReader(System.in)).readLine();
		return str;
	}
	//获得值映射
	public static HashMap<String,Integer> getValue(String expStr) throws IOException{
		HashMap<String,Integer> map = new HashMap<>();
		char[] arr = expStr.toCharArray();
		//解析有几个参数要传递
		for (char c : arr) {
			if(c!='+' && c!='-'){
				if(!map.containsKey(String.valueOf(c))){//解决重复参数的问题
					System.out.println("请输入"+c+"的值：");
					String in = new BufferedReader(new InputStreamReader(System.in)).readLine();
					map.put(String.valueOf(c), Integer.valueOf(in));
				}
			}
		}
		return map;
	}
}
