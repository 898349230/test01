package concurr2.ch2.synblock;

public class Service {

	private String username;
	private String password;
	String anything = new String();
	
	public void setValue(String username, String password) {
		try {
//			String anything = new String();
			synchronized (anything) {
				System.out.println("begin 进入同步块  threadName=" + Thread.currentThread().getName());
				this.username = username;
				Thread.sleep(2000);
				this.password = password;
				System.out.println("end 离开同步块  threadName=" + Thread.currentThread().getName());
			}
			System.out.println("username=" + this.username + "  password=" + this.password);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
