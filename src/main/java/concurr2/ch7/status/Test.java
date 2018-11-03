
package concurr2.ch7.status;

import java.lang.Thread.State;

public class Test {

	public static void main(String[] args) {
		/**
		 * 线程状态：
		 * 	NEW : 新建线程，还未start()
		 * 	RUNNABLE : 运行状态
		 *	BLOCKED ：阻塞状态
		 *	WAITING ：等待，执行 wait()
		 *	TIMED_WAITING : 执行sleep()方法
		 *	TERMINATED ：已销毁的线程
		 */
		State blocked = State.BLOCKED;
		System.out.println(blocked);
	}
}
