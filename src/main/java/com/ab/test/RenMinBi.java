package com.ab.test;

public class RenMinBi {
	/**
	 * @param args
	 *            add by zxx ,Nov 29, 2008
	 */
	private static final char[] data ={ '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖' };
	private static final char[] units ={ '元', '拾', '佰', '仟', '万', '拾', '佰', '仟', '亿' };

	public static void main(String[] args) {
		System.out.println(convert(135689123));
		System.out.println(convert(135000003));
	}

	public static String convert(int money) {
		StringBuffer sbf = new StringBuffer();
		int unit = 0;
		while (money != 0) {
			sbf.insert(0, units[unit++]);
			int number = money % 10;
			sbf.insert(0, data[number]);
			money /= 10;
		}
		return sbf.toString();
	}
	/**
	 * 去零代码
	 * @param str
	 * @return
	 */
	public static String quLing(StringBuilder str){
		return str.reverse().toString()
				.replaceAll("零[拾佰仟]","零")
				.replaceAll("零+万","万")
				.replaceAll("零+元","元")
				.replaceAll("零+","零");
	}
}