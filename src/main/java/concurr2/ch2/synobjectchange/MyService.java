package concurr2.ch2.synobjectchange;

public class MyService {

	private String lock = "123";
	private UserInfo user = new UserInfo("张三", "123456");
	public void methodA() {
		try {
			synchronized(user){
//			synchronized(lock){
				System.out.println(Thread.currentThread().getName() + " begin");
				// 改变锁对象
				lock = "456";
				user.setName("李四");
				Thread.sleep(2000);
				System.out.println(Thread.currentThread().getName() + " end");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
