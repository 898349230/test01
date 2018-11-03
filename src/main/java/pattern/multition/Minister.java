package pattern.multition;

public class Minister {
	public static void main(String[] args) {			
		for(int i=0;i<10;i++){
			Emporer emporer = Emporer.getInstance();
			System.out.print("第	"+(i+1)+" 个大臣参见的是  ");
			emporer.emporerInfo();
		}
	}
}
