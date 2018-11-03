package pattern.staticfactory;

public class NvWa {
	public static void main(String[] args) {
		Human human = null;
		//白人
		human = HumanFactory.createHuman(WhiteHuman.class);
		human.eat();
		human.laugh();
		human.run();
		//黑人
		human = HumanFactory.createHuman(BlackHuman.class);
		human.eat();
		human.laugh();
		human.run();
		//黄种人
		human = HumanFactory.createHuman(YelloHuman.class);
		human.eat();
		human.laugh();
		human.run();
		
		System.out.println("============随机生成人类==============================");
		
		for(int i=0;i<100;i++){
			human = HumanFactory.createHuman();
			human.eat();
			human.run();
			human.laugh();
		}
		
	}
}
