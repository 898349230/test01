package pattern.command;

/**
 * 命令调用者
 * @author Administrator
 *
 */
public class Invoker {
	private Command command;
	public void setCommand(Command command){
		this.command = command;
	}
	public void action(){
		this.command.excute();
	}
}
