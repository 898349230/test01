package pattern.command;

public class DeleteCommand extends Command{

	@Override
	void excute() {
		super.rg.find();
		super.pg.delete();
		super.cg.delete();
	}

}
