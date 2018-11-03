package com.ab.jdbc;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBUti {
	//创建连接对象
	private Connection conn = null;
	private PreparedStatement ps = null;
	public ResultSet rs = null;
	
	//配置数据库连接字符串
	//userUnicode		：表示使用Unicode编码格式进行编码
	//characterEncoding	：设置编码格式为utf-8
	//如果没有采用上述参数，那么在存储数据过程可能会出现乱码
	private String url = "jdbc:mysql://localhost:3306/wisdb?useUnicode=true&characterEncoding=utf-8";
	private String user = "root";
	private String password = "sa";
	
	/**
	 * 静态代码块，初始化基本对象
	 */
	static{
		//加载驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 初始化连接对象
	 */
	private void init(){
		//获取连接对象
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 执行非查询语句，eg：insert、delete 及update
	 * @param sql			：待执行的t-sql语句
	 * @param parameters	：语句的参数
	 * @return				：受影响行数
	 */
	public int execNoneQuery(String sql, Object...parameters){	
		init();
		try {
			//创建PreparedStatement对象
			ps = 	conn.prepareStatement(sql);
			
			//初始化参数
			for(int i = 0;i<parameters.length;i++){
				ps.setObject((i+1), parameters[i]);
			}
			
			//执行语句			
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			close();
		}
		return 0;
	}
	
	/**
	 * 执行查询语句，返回查询结果集
	 * @param sql			：待执行的t-sql语句
	 * @param parameters	：语句的参数
	 * @return				：查询结果集
	 */
	public ResultSet execQuery(String sql, Object...parameters){	
		init();
		try {
			//创建PreparedStatement对象
			ps = conn.prepareStatement(sql);
			//初始化参数
			for(int i = 0;i<parameters.length;i++){
				ps.setObject((i+1), parameters[i]);
			}
			
			//执行查询
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * 执行查询语句并并返回实体对象
	 * @param <T>			：参数类型占位符
	 * @param t				：Class类型的对象
	 * @param sql			：T-Sql语句
	 * @param parameters	：参数集合
	 * @return				：对象结果集
	 */
	public <T> T getSingleModel(Class<T> t,String sql, Object...parameters){
		init();
		
		try {
			//创建PreparedStatement对象
			ps = conn.prepareStatement(sql);
			
			//初始化参数
			for(int i = 0;i<parameters.length;i++){
				ps.setObject((i+1), parameters[i]);
			}
			
			//执行Sql语句
			rs = ps.executeQuery();
			
			if(rs.next()){
				//ResultSetMetaData	:用于描述ResultSet对象的对象
				//通过该类型的对象可以获取到ResultSet的相关属性和方法
				ResultSetMetaData  meta = rs.getMetaData();
				//getColumnCount的作用是用于获取列的数量
				//System.out.println(meta.getColumnCount());
				
				//实例化一个Model对象(通过反射实现)
				T obj = t.newInstance();
				
				//遍历ResultSet的列
				for(int i = 0;i<meta.getColumnCount();i++){
					//获取列名
					String name = meta.getColumnLabel(i+1);					
					
					//获取Class类型对象t中的字段
					Field field = t.getDeclaredField(name);
					//设置访问权限
					field.setAccessible(true);
					//为字段赋值
					field.set(obj, rs.getObject(name));
				}
				return obj;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			close();
		}
		
		return null;		
	}
	
	public <T> List<T> getModelList(Class<T> t,String sql, Object...parameters){
		init();
		
		try {
			//创建PreparedStatement对象
			ps = conn.prepareStatement(sql);
			
			//初始化参数
			for(int i = 0;i<parameters.length;i++){
				ps.setObject((i+1), parameters[i]);
			}
			
			//执行Sql语句
			rs = ps.executeQuery();
			
			if(rs != null){
				//定义Model类型的集合				
				List<T> list = new ArrayList<T>();
				//遍历数据
				while(rs.next()){
					//实例化一个Model对象
					T obj = t.newInstance();
					//获取ResultSet的属性和方法
					ResultSetMetaData meta = rs.getMetaData();
					//通过循环遍历ResultSet的列
					for(int i= 1;i<=meta.getColumnCount();i++){
						//获取ResultSet列名称
						String name = meta.getColumnLabel(i);
												
						//获取Model中的字段
						Field field = t.getDeclaredField(name);
						
						//设置字段的值
						field.setAccessible(true);
						field.set(obj, rs.getObject(name));
					}
					list.add(obj);
				}
				return list;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			close();
		}
		
		return null;
	}
	
	
	public int getCount(String sql, Object...parameters){
		int count = 0;
		init();
		try {
			//创建PreparedStatement对象
			ps = conn.prepareStatement(sql);
			//初始化参数
			//初始化参数
			for(int i = 0;i<parameters.length;i++){
				ps.setObject((i+1), parameters[i]);
			}
			
			//执行查询
			rs = ps.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			close();
		}
		return count;
	}
	
	/**
	 * 关闭所有资源
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
			// TODO Auto-generated catch block
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
