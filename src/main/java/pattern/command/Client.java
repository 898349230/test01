package pattern.command;

public class Client {
	public static void main(String[] args) {
		Invoker invoker = new Invoker();
		//删除命令
		//Command command = new DeleteCommand();
		//增加命令
		Command command = new AddCommand();
		invoker.setCommand(command);
		invoker.action();
	}
}
