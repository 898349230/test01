package concurr2.ch1.suspendthread;

public class MyObject {
	private String name = "a";
	private String password = "aa";
	
	public void setValue(String name, String password) {
		this.name = name;
		if(Thread.currentThread().getName().equals("A")) {
			System.out.println("暂停线程中。。。。。");
			Thread.currentThread().suspend();
		}
		this.password = password;
	}

	@Override
	public String toString() {
		return "name=" + name + " password=" + password;
	}
}
