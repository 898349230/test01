package testpro.test01;


public class Permmission {
	public static void main(String[] args) {
		Integer a = 1, b = 2, c = 4, d = 8 , e = 16;
		Integer p1 = a | b | c | d | e | 32 | 64;
		System.out.println("p1= " + p1);
		Integer p2 = (p1 & c);
		boolean has = (p2 == c);
		System.out.println(p2.hashCode() + "\t" + c.hashCode());
		Permmission p = new Permmission();
		p.hashCode();
		System.out.println(has);
	}
}
