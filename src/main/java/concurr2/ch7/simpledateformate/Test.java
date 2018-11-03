package concurr2.ch7.simpledateformate;

import java.text.SimpleDateFormat;

public class Test {

	public static void main(String[] args) {
		
//		testUnSafe();
		
//		testSafe01();
		
		testSafe02();
	}
	
	/**
	 * 测试 SimpleDateFormat 是线程不安全的
	 */
	public static void testUnSafe() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String[] dateArr = new String[]{"2018-12-06", "2018-10-16", "2018-12-26", "2018-05-16", "2018-10-16", 
				"2018-05-12", "2018-12-19", "2018-01-06", "2018-03-06", "2018-02-03"};
		
		MyThread01[] threadArr = new MyThread01[dateArr.length];
		
		for(int i = 0; i < 10; i++ ) {
			threadArr[i] = new MyThread01(sdf, dateArr[i]);
		}
		
		for(int i = 0; i < 10; i++ ) {
			threadArr[i].start();
		}
	} 
	
	
	/**
	 * 测试 线程安全的 SimpleDateFormat
	 */
	public static void testSafe01() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String[] dateArr = new String[]{"2018-12-06", "2018-10-16", "2018-12-26", "2018-05-16", "2018-10-16", 
				"2018-05-12", "2018-12-19", "2018-01-06", "2018-03-06", "2018-02-03"};
		
		MyThread02[] threadArr = new MyThread02[dateArr.length];
		
		for(int i = 0; i < 10; i++ ) {
			threadArr[i] = new MyThread02(sdf, dateArr[i]);
		}
		
		for(int i = 0; i < 10; i++ ) {
			threadArr[i].start();
		}
	} 
	
	
	/**
	 * 测试 线程安全的 SimpleDateFormat
	 */
	public static void testSafe02() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String[] dateArr = new String[]{"2018-12-06", "2018-10-16", "2018-12-26", "2018-05-16", "2018-10-16", 
				"2018-05-12", "2018-12-19", "2018-01-06", "2018-03-06", "2018-02-03"};
		
		MyThread03[] threadArr = new MyThread03[dateArr.length];
		
		for(int i = 0; i < 10; i++ ) {
			threadArr[i] = new MyThread03(sdf, dateArr[i]);
		}
		
		for(int i = 0; i < 10; i++ ) {
			threadArr[i].start();
		}
	} 
	
}
