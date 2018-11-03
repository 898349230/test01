package pattern.adapter;

/**
 * 适配器(关联形式)
 * @author Administrator
 *
 */
public class OuterUserInfo2 implements IUserInfo{
	private IOuterUser outerInfo = new OuterInfo();
	
	@Override
	public String getUserName() {
		String string = outerInfo.getUserBaseInfo().get("员工姓名");
		System.out.println("员工姓名是  ："+string);
		return string;
	}

	@Override
	public String getUserJobPosition() {
		String string = outerInfo.getUserJobInfo().get("职位");
		System.out.println("职位 ："+string);
		return string;
	}

	@Override
	public String getUserHomeTel() {
		String string = outerInfo.getUserHomeInfo().get("家庭电话");
		System.out.println("家庭电话 ："+string);
		return string;
	}

}
