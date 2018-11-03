package pattern.command;
/**
 * 命令抽象类
 * @author Administrator
 *
 */
public abstract class Command {
	protected RequiredGroup rg = new RequiredGroup();
	protected PageGroup pg = new PageGroup();
	protected CodeGroup cg = new CodeGroup();
	
	abstract void excute();
}
