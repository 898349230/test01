package pattern.facade;

public class Client {
	public static void main(String[] args) {
		String address = "北京昌平區回龍觀";
		String content = "hello  好久不見";
		
		ModelPostOffice postOffice = new ModelPostOffice();
		postOffice.sendLetter(content, address);
	}
}
