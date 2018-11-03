package concurr2.ch3.waitnotifyaction;

public class BackupA extends Thread{

	private DBTools tool ;
	
	public BackupA(DBTools tool) {
		this.tool = tool;
	}
	
	@Override
	public void run() {
		tool.backupA();
	}
	
}
