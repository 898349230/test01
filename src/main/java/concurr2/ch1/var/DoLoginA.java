package concurr2.ch1.var;

public class DoLoginA extends Thread{

	@Override
	public void run() {
		LoginServlet.login("a", "aa");
	}
	
}
