import java.security.*;

public class TestDemo {

	public static void main(String[] args) throws InterruptedException {
		System.out.println(Integer.parseInt("0001111"));
		System.out.println(Integer.parseInt("0011111"));
		System.out.println(Integer.parseInt("0111111"));
		System.out.println(Integer.parseInt("0001111", 2));
		System.out.println(Integer.parseInt("0011111", 2));
		System.out.println(Integer.parseInt("0111111", 2));
		System.out.println(Integer.parseInt("0001111", 2) & 15);
		System.out.println(Integer.parseInt("0011111", 2) & 15);
		System.out.println(Integer.parseInt("0111111", 2) & 15);
		
		
		testSal();
	}
	
	private static void testSal() {
//					7									12

	}

	private static void testRSA() throws NoSuchAlgorithmException {
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
		keyPairGen.initialize(1024);
		KeyPair keyPair = keyPairGen.generateKeyPair();
        PublicKey aPublic = keyPair.getPublic();
        PrivateKey aPrivate = keyPair.getPrivate();
        byte[] publicEncoded = aPublic.getEncoded();
        byte[] privateEncoded = aPrivate.getEncoded();

    }


}
