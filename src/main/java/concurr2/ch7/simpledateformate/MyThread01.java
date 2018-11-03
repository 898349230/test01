package concurr2.ch7.simpledateformate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyThread01 extends Thread{

	private SimpleDateFormat sdf;
	private String dateStr;
	
	public MyThread01(SimpleDateFormat sdf, String dateStr) {
		this.sdf = sdf;
		this.dateStr = dateStr;
	}
	
	@Override
	public void run() {
		try {
			Date date = sdf.parse(dateStr);
			String newDateStr = sdf.format(date).toString();
			if(!dateStr.equals(newDateStr)) {
				System.out.println(Thread.currentThread().getName() + " 线程不安全  传入的dateStr ： " + dateStr + " 转换后的dateStr ：" + newDateStr);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
}
