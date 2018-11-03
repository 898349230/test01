package com.ab.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestJDBC {
	private String url;
	private String password;
	private String username;
	
	private Connection connection = null;;
	private PreparedStatement ps=null;
	private ResultSet rs =null;
	
	static{
		try {
			Class.forName("");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public int noQuery(String sql){
		try {
			connection = DriverManager.getConnection(url,username,password);
			 ps = connection.prepareStatement(sql);
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.close();
		}
		return 0;
	}
	public ResultSet query(String sql){
		try {
			connection = DriverManager.getConnection(url,username,password);
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	private void close(){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(ps!=null){
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(connection!=null){
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
