package concurr2.ch7.simpledateformate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTools2 {

	private static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>();
	
	public static Date parse(String pattern, String dateStr) throws ParseException {
		SimpleDateFormat sdf  = threadLocal.get();
		if(null == sdf) {
			sdf = new SimpleDateFormat(pattern);
		}
		Date date = sdf.parse(dateStr);
		return date;
	}  
	
	public static String formate(String pattern, Date date) {
		SimpleDateFormat sdf  = threadLocal.get();
		if(null == sdf) {
			sdf = new SimpleDateFormat(pattern);
		}
		String string = sdf.format(date);
		return string;
	}
	
}
