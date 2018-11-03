package pattern.command;

public  class RequiredGroup implements Group{

	@Override
	public void find() {
		System.out.println("找到需求组。。。");
	}

	@Override
	public void add() {
		System.out.println("需求组增加功能");
	}

	@Override
	public void delete() {
		System.out.println("需求组删除功能");		
	}

	@Override
	public void update() {
		System.out.println("需求组修改功能");		
		
	}

}
