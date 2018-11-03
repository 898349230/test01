package concurr2.ch7.simpledateformate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTools {

	public static Date parse(String pattern, String dateStr) throws ParseException {
		return new SimpleDateFormat(pattern).parse(dateStr);
	}  
	
	public static String formate(String pattern, Date date) {
		return new SimpleDateFormat(pattern).format(date);
	}
	
}
