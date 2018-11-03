package com.ab.timetask;

import java.util.TimerTask;

/**
 * 
 * @author Administrator
 *
 */
public class MyTask extends TimerTask{

	private static int i =0;
	
	@Override
	public void run() {
		System.out.println(" task  "+i++);
	}

}
