package com.ab.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 工具类
 * 提供用于执行T-Sql的方法
 * 
 * @author Terry
 * 
 */
public class DBUtil {
	// 数据访问对象
	Connection conn = null;
	PreparedStatement ps = null;
	public ResultSet rs = null;

	// 数据库连接字符串
	private static String url;
	private static String user;
	private static String pwd;

	/**
	 * 静态代码块 静态代码块的特点：在类加在后自动被调用 作用：加载数据库驱动类
	 */
	static {
		//class.getResourceAsStream()	：将物理文件转为流媒体对象
		InputStream stream = DBUtil.class.getResourceAsStream("/config.properties");
		//Properties表示一个属性的集合
		//可以从流中读取属性集合
		Properties prop = new Properties();
		
		
		try {
			//装载流对象
			prop.load(stream);
			//getProperty()	：读取属性值
			Class.forName(prop.getProperty("driver"));
			
			//读取属性值
			url = prop.getProperty("url");
			user = prop.getProperty("user");
			pwd = prop.getProperty("password");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 无参数构造函数
	 */
	public DBUtil(){
		
	}
	
	/**
	 * 初始化连接对象
	 */
	private void init(){
		// 2、获取连接对象
		try {
			conn = DriverManager.getConnection(url, user, pwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 执行非查询语句
	 * eg:insert、delete、update
	 * @param sql	：待执行的T-SQL语句
	 * @return		：受影响行数
	 */
	/*
	public int execUpdate(String sql) {
		init();
		try {
			//获取PreparedStatement对象
			ps = conn.prepareStatement(sql);
			//发送对象并返回受影响行数
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			close();
		}
		return 0;	
	}
	*/
	/**
	 * 执行带有参数的非查询语句
	 * eg:insert、delete、update
	 * @param sql			：待执行的T-Sql语句
	 * @param parameters	：参数集合
	 * @return				：受影响行数
	 */
	public int execUpdate(String sql, Object...parameters){
		init();
		try {
			//获取PreparedStatement对象
			ps = conn.prepareStatement(sql);
			//绑定语句参数
			for(int i =1;i<=parameters.length;i++){
				ps.setObject(i, parameters[i-1]);
			}
			
			//发送对象并返回受影响行数
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			close();
		}
		return 0;	
	}

	/**
	 * 执行查询语句
	 * 数据读取结束后需要调用close方法
	 * @param sql	：待执行T-Sql语句
	 * @return		：查询结果集对象
	 */
	/*
	public ResultSet execQuery(String sql) {
		init();
		try {
			//获取PreparedStatement对象
			ps = conn.prepareStatement(sql);
			//发送语句并返回响应结果
			return ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} 		
		return null;
	}
	*/
	
	/**
	 * 执行待参数的查询语句
	 * @param sql			：待执行的T-Sql语句
	 * @param parameters	：查询参数集合
	 * @return				：查询结果集对象
	 */
	public ResultSet execQuery(String sql,Object...parameters) {
		init();
		try {
			//获取PreparedStatement对象
			ps = conn.prepareStatement(sql);
			//绑定语句参数
			for(int i =1;i<=parameters.length;i++){
				ps.setObject(i, parameters[i-1]);
			}
			//发送语句并返回响应结果
			return ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} 		
		return null;
	}
	
	/**
	 * 关闭数据访问对象
	 */
	 public void close(){
		try {
			if(rs != null)
				rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			if(ps != null)
				ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if(conn != null)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
