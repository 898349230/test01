package concurr2.ch7.simpledateformate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import sun.net.smtp.SmtpProtocolException;

public class MyThread02 extends Thread{

	private SimpleDateFormat sdf;
	private String dateStr;
	
	public MyThread02(SimpleDateFormat sdf, String dateStr) {
		this.sdf = sdf;
		this.dateStr = dateStr;
	}
	
	@Override
	public void run() {
		try {
			Date date = DateTools.parse("yyyy-MM-dd", dateStr);
			String newDateStr = DateTools.formate("yyyy-MM-dd", date);
			if(!dateStr.equals(newDateStr)) {
				System.out.println(Thread.currentThread().getName() + " 线程不安全  传入的dateStr ： " + dateStr + " 转换后的dateStr ：" + newDateStr);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
}
