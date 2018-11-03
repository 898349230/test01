package pattern.command;

public  class CodeGroup implements Group{

	@Override
	public void find() {
		System.out.println("找到代码组。。。");
	}

	@Override
	public void add() {
		System.out.println("代码组增加功能");
	}

	@Override
	public void delete() {
		System.out.println("代码组删除功能");		
	}

	@Override
	public void update() {
		System.out.println("代码组修改功能");		
		
	}

}
