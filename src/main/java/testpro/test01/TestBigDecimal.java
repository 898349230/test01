package testpro.test01;

import java.math.BigDecimal;

public class TestBigDecimal {
	
	public static void main(String[] args) {
		String restlt = add("5.6","3.0005");
		String restlt2 = min("5.6","3.0005");
		double restlt3 = div("5.6","3.0005");
		double restlt4 = div2("5.6","3.0005");
		
		BigDecimal bigDecimal = new BigDecimal("6.101");
		BigDecimal bigDecimal2 = new BigDecimal(6.101);
		System.out.println(bigDecimal);
		System.out.println(bigDecimal2);
		System.out.println(restlt);
		System.out.println(restlt2);
		System.out.println(restlt3);
		System.out.println( "restlt4 :\t" + restlt4);
		
		System.out.println("********************  Integer\t"+Integer.MIN_VALUE+"\t"+Integer.MAX_VALUE);
		System.out.println("********************  Short\t"+Short.MIN_VALUE+"\t"+Short.MAX_VALUE);
		System.out.println("********************  Long\t"+Long.MIN_VALUE+"\t"+Long.MAX_VALUE);
		System.out.println("********************  Float\t"+Float.MIN_VALUE+"\t"+Float.MAX_VALUE);
		System.out.println("********************  Double\t"+Double.MIN_VALUE+"\t"+Double.MAX_VALUE);
		
		
	}
	
	public static String add(String a, String b) {
		BigDecimal aa = new BigDecimal(a);
		BigDecimal bb = new BigDecimal(b);
		BigDecimal result = aa.add(bb);
		return result.toString();
	}
	
	public static String min(String a, String b) {
		BigDecimal aa = new BigDecimal(a);
		BigDecimal bb = new BigDecimal(b);
		BigDecimal result = aa.subtract(bb);
		return result.toString();
	}
	
	public static double div(String a, String b) {
		BigDecimal aa = new BigDecimal(a);
		BigDecimal bb = new BigDecimal(b);
		double value = aa.divide(bb,5).doubleValue();
		return value;
	}
	
	public static double div2(String a, String b) {
		BigDecimal aa = new BigDecimal(a);
		BigDecimal bb = new BigDecimal(b);
		double value = aa.divide(bb,5).byteValue();
		return value;
	}
}
