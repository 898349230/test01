package concurr2.ch1.var;

public class LoginServlet {

	private static String usernameRef;
	private static String passwordRef;
	
//	synchronized    
	public static void login(String username, String password) {
		try {
			usernameRef = username;
			if(username.equals("a")) {
				System.out.println("a  sleep前  " + usernameRef + "  " + passwordRef);
				Thread.sleep(5000);
				System.out.println("a  sleep后  " + usernameRef + "  " + passwordRef);
			}
			passwordRef = password;
			System.out.println(usernameRef + "  " + passwordRef);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
