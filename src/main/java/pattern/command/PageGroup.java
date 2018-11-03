package pattern.command;

public  class PageGroup implements Group{

	@Override
	public void find() {
		System.out.println("找到美工组");
	}

	@Override
	public void add() {
		System.out.println("美工组增加功能");
	}

	@Override
	public void delete() {
		System.out.println("美工组删除功能");		
	}

	@Override
	public void update() {
		System.out.println("美工组修改功能");		
		
	}

}
