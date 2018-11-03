package concurr2.ch1.var;

public class DoLoginB extends Thread{

	@Override
	public void run() {
		LoginServlet.login("b", "bb");
	}
	
}
