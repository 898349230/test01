package pattern.decorate;

/**
 * 抽象构件：原始的功能接口-----
 * 相当于i/o流里面InputStream/OutputStream和Reader/Writer
 * @author Administrator
 *
 */
public abstract class SchoolReport {
	public abstract void report();
	public abstract void sign(String name);
}
