package pattern.adapter;

public class UserInfo implements IUserInfo{

	@Override
	public String getUserName() {
		System.out.println("员工姓名是  ：  ");
		return null;
	}

	@Override
	public String getUserJobPosition() {
		System.out.println("员工职位是    ：　");
		return null;
	}

	@Override
	public String getUserHomeTel() {
		System.out.println("住宅电话是  ： ");
		return null;
	}

}
