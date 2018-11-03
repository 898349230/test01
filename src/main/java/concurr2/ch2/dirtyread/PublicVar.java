package concurr2.ch2.dirtyread;

public class PublicVar {

	private String username = "A";
	private String password = "AA";
	
	synchronized public void setValue(String username, String password) {
		try {
			this.username = username;
			System.out.println("setValue()  threadName " + Thread.currentThread().getName() );
			Thread.sleep(3000);
			this.password = password;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	synchronized
	public void getValue() {
		System.out.println( "ThreadName "+ Thread.currentThread().getName() + "  username= " + this.username + "  password= " + this.password);
	}
}
