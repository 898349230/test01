package pattern.abstractfactory;

public abstract class AbstractHumanFactory implements HumanFactory {

	protected Human createHuman(HumanEnum humanEnum){
		Human human = null;
		if(!humanEnum.getValue().equals("")){
			try {
				human = (Human) Class.forName(humanEnum.getValue()).newInstance();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return human;
	}
	
}
