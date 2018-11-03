package pattern.observer;


public class Client {
	public static void main(String[] args) {
		HanFeiZi hanFeiZi = new HanFeiZi();
		
		Lisi lisi = new Lisi();
		Wangsi wangsi = new Wangsi();
		
		hanFeiZi.addObserver(lisi);
		hanFeiZi.addObserver(wangsi);
		
		hanFeiZi.haveFun();
	}
}
