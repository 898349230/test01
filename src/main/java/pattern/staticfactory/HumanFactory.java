package pattern.staticfactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class HumanFactory {
	
	private static Map<String,Human> humans = new HashMap<String,Human>();
	/**
	 * 通过Class实例化具体对象
	 * @param c
	 * @return
	 */
	public static Human createHuman(Class c){
		Human human = null;
		if(humans.containsKey(c.getSimpleName())){
			return humans.get(c.getSimpleName());
		}
		try {
			human = (Human) Class.forName(c.getName()).newInstance();
			humans.put(c.getSimpleName(), human);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			System.out.println("实例化对象有误");
		}
		return human;
	}
	/**
	 * 随机实例化对象
	 * @return
	 */
	public static Human createHuman(){
		Human human = null;
		List<Class> humanClass= ClassUtils.getAllClassByInterface(Human.class);
		Random random = new Random();
		int i = random.nextInt(humanClass.size());
		human = createHuman(humanClass.get(i));
		return human;
	}
	
}
