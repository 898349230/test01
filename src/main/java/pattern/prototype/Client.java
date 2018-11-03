package pattern.prototype;

import java.util.Random;

/**
 * 原型设计模式
 * 适用场景：一是类初始化需要消化非常多的资源，这个资源包括数据、硬件资源等；
 * 		    二是通过new产生一个对象需要非常繁琐的数据准备或访问权限，则可以使用原型模式；
 * 		    三是一个对象需要提供给其他对象访问，而且各个调用者可能都需要修改其值时，可以考虑使用原型模式拷贝多个对象供调用者使用。
 * 	在实际项目中，原型模式很少单独出现，一般是和工厂方法模式一起出现，通过clone的方法创建一个对象，然后由工厂方法提供给调用者。
 * @author Administrator
 *
 */
public class Client {
	
	//发送账单的数量，这个值是从数据库中获得
	private static int MAX_COUNT =6;
	public static void main(String[] args) {
		//模拟发送邮件
		int i =0;
		Mail mail = new Mail(new AdvTemplate());
		mail.setTail("xx银行版权所有");
		while(i<MAX_COUNT){
			//以下是每封邮件不同的地方
			Mail cloneMail = mail.clone();
			cloneMail.setAppellation(getString(5)+" 先生/女士");
			cloneMail.setReceiver(getString(5)+"@"+getString(8)+".com");
			//发送邮件
			sendMail(cloneMail);
			i++;
		}
	}
	/**
	 * 发送邮件
	 */
	public static void sendMail(Mail mail){
		System.out.println("标题："+mail.getSubject()+"\t 收件人"+mail.getReceiver()+"\t发送成功！");
	}
	/*
	 * 获取指定长度字符串
	 */
	public static String getString(int length){
		String str = "abvdefghijklmnopqrstuvwxyz";
		Random r = new Random();
		String result = "";
		for(int i=0;i<length;i++){
			result += str.charAt(r.nextInt(str.length()));
		}
		return result;
	}
}
