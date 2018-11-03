package pattern.adapter;

import java.util.Map;

/**
 * 适配器(继承形式)
 * @author Administrator
 *
 */
public class OuterUserInfo extends OuterInfo implements IUserInfo{

	private Map<String,String> baseInfo = super.getUserBaseInfo();
	private Map<String,String> jobInfo = super.getUserJobInfo();
	private Map<String,String> homeInfo = super.getUserHomeInfo();
	
	@Override
	public String getUserName() {
		String str = this.baseInfo.get("员工姓名");
		System.out.println("员工姓名" + str);
		return str;
	}

	@Override
	public String getUserJobPosition() {
		String str = jobInfo.get("职位");
		System.out.println("职位" + str);
		return str;
	}

	@Override
	public String getUserHomeTel() {
		String str = homeInfo.get("家庭电话");
		System.out.println("家庭电话" + str);
		return str;
	}

}
