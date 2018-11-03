package concurr2.ch3.waitnotifyaction;

public class Test {

	public static void main(String[] args) {
		
		/**
		 * 创建20个线程，10个线程给A数据库备份数据， 10个给B数据库备份
		 * 并且交叉进行
		 */
		DBTools tool = new DBTools();
		for(int i = 0; i < 20; i++) {
			BackupA threadA = new BackupA(tool);
			threadA.start();
			BackupB threadB = new BackupB(tool);
			threadB.start();
		}
		
	}
}
