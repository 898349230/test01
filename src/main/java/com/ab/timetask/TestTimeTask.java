package com.ab.timetask;

import java.util.Timer;

public class TestTimeTask {
	public static void main(String[] args) {
		Timer timer = new Timer();
		MyTask task = new MyTask();
		timer.schedule(task, 10	, 1000);
	}
}
