package concurr2.ch3.waitnotifyaction;

public class BackupB extends Thread{

	private DBTools tool ;
	
	public BackupB(DBTools tool) {
		this.tool = tool;
	}
	
	@Override
	public void run() {
		tool.backupB();
	}
	
}
