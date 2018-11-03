package pattern.adapter;

import java.util.HashMap;
import java.util.Map;

public class OuterInfo implements IOuterUser{

	@Override
	public Map<String, String> getUserBaseInfo() {
		Map<String,String> map = new HashMap<String,String>();
		map.put("员工姓名", "张三");
		return map;
	}

	@Override
	public Map<String, String> getUserJobInfo() {
		Map<String,String> map = new HashMap<String,String>();
		map.put("职位", "工程师");
		return map;
	}

	@Override
	public Map<String, String> getUserHomeInfo() {
		Map<String,String> map = new HashMap<String,String>();
		map.put("家庭电话", "123456");
		return map;
	}

}
