package com.ab.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class JDBC {
//	private static String url ="jdbc:oracle:thin:@localhost:1521:orcl";
//	private static String username = "scott";
//	private static String password = "tiger";
//	private static String driver = "oracle.jdbc.driver.OracleDriver";
	private static String url;
	private static String username ;
	private static String password ;
	private static String driver ;
	
	private static Connection connection = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	// 调用存储过程
	private CallableStatement prepareCall;
	
	static {
		InputStream is = JDBC.class.getResourceAsStream("/config.properties");
		Properties prop = new Properties();
		try {
			prop.load(is);
			driver = prop.getProperty("driver");
			url = prop.getProperty("url");
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	public List<Dept> query(){
		try {
			ps = connection.prepareStatement("select * from dept");
			rs = ps.executeQuery();
			List<Dept> list = new ArrayList<Dept>();
			while(rs.next()){
				Dept dept = new Dept();
				dept.setDeptNo(rs.getString(1));
				dept.setdName(rs.getString(2));
				dept.setLoc(rs.getString("loc"));
				list.add(dept);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 批量插入
	 */
	public void batchInsert(){
		try {
			
		     /*Statement statement = connection.createStatement();
			 statement.addBatch("insert into dept values(11,'33','44')");
			 statement.addBatch("insert into dept values(22,'55','66')");
			 statement.addBatch("insert into dept values(33,'77','88')");
			 int[] arr = statement.executeBatch();*/
			 
			 ps = connection.prepareStatement("insert into dept values(?,?,?)");
			 ps.setInt(1, 11);
			 ps.setString(2, "22");
			 ps.setString(3, "33");
			 ps.addBatch();
			 
			 ps.setInt(1, 44);
			 ps.setString(2, "55");
			 ps.setString(3, "66");
			 ps.addBatch();
			 
			 int[] arr = ps.executeBatch();
			 
			 System.out.println(Arrays.toString(arr));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 调用存储过程
	 * 
	 *     create or replace procedure 
           		p2(v_a in number , v_b in number , v_out out number , v_d in out number)
		    is
		    begin 
		      if(v_a > v_b) then
		             v_out := v_a;
		      else
		        v_out := v_b;
		      end if;
		      v_d := v_d+1;
		    end;
	 * 
	 * @param args
	 */
	public int[] doProcedure(){
		try {
			prepareCall = connection.prepareCall("{call p2(?,?,?,?)}");
			
			// 注册 out 类型参数类型
			prepareCall.registerOutParameter(3,Types.INTEGER);
			prepareCall.registerOutParameter(4,Types.INTEGER);
			
			// 为  in 类型参数赋值
			prepareCall.setInt(1,3);
			prepareCall.setInt(2,5);
			prepareCall.setInt(4,6);
			
			// 执行存储过程
			prepareCall.execute();
			
			// 获取  out 的值
			int v_out = prepareCall.getInt(3);
			int v_d = prepareCall.getInt(4);
			
			int[] arr= {v_out,v_d};
			return arr;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				prepareCall.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	public static void main(String[] args) {
		JDBC j = new JDBC();
//		List<Dept> list = j.query();
//		for (Dept dept : list) {
//			System.out.println(dept);
//		}
		
		//存储过程
//		int[] arr = j.doProcedure();
//		System.out.println(Arrays.toString(arr));
		
		j.batchInsert();
	}
}
