package pattern.abstractfactory;

public class MaleHumanFactory extends AbstractHumanFactory {

	@Override
	public Human createBlackHuman() {
		return super.createHuman(HumanEnum.BlackMaleHuman);
	}

	@Override
	public Human createWhiteHuman() {
		return super.createHuman(HumanEnum.WhiteMaleHuman);
	}

	@Override
	public Human createYellowHuman() {
		return super.createHuman(HumanEnum.YelloMaleHuman);
	}

}
