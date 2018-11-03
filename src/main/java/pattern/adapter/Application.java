package pattern.adapter;

public class Application {

	public static void main(String[] args) {
		//使用继承类型的  适配器
		System.out.println("============继承类型的适配器================");
		IUserInfo user = new OuterUserInfo();
		user.getUserName();
		user.getUserHomeTel();
		user.getUserJobPosition();
		
		//使用关联关系类型的 适配器
		System.out.println("============关联类型的适配器================");
		IUserInfo user2 = new OuterUserInfo2();
		user2.getUserName();
		user2.getUserHomeTel();
		user2.getUserJobPosition();
	}
}
