package pattern.decorate;

public class Client {
	public static void main(String[] args) {
		SchoolReport sr = new ForthGradeReport();
		//sr = new HighScoreDecorate(sr);
		sr = new SortDecorate(sr);
		sr.report();
		sr.sign("老爹");
	}
}
