package pattern.command;

public class AddCommand extends Command{

	@Override
	void excute() {
		super.rg.find();
		super.pg.add();
		super.rg.add();
	}

}
