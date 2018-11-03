package pattern.abstractfactory;

public enum HumanEnum {
	
	YelloMaleHuman("com.ab.pattern.abstractfactory.YellowMaleHuman"),
	YelloFemaleHuman("com.ab.pattern.abstractfactory.YellowFemaleHuman"),
	WhiteFemaleHuman("com.ab.pattern.abstractfactory.WhiteFemaleHuman"),
	WhiteMaleHuman("com.ab.pattern.abstractfactory.WhiteMaleHuman"),
	BlackFemaleHuman("com.ab.pattern.abstractfactory.BlackFemaleHuman"),
	BlackMaleHuman("com.ab.pattern.abstractfactory.BlackMaleHuman");
	
	private String value = "";
	private HumanEnum(String value){
		this.value = value;
	}
	public String getValue(){
		return this.value;
	}
}
