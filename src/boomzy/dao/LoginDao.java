package boomzy.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import boomzy.entity.User;

public class LoginDao {
	//Dao层访问数据库实现登陆操作
	public static int login(User user){ 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = -1;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/train?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String use = "root";
			String password = "123456";
			conn = DriverManager.getConnection(url, use, password);
			String sql = "select count(*) from user where name=? and password=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			rs = pstmt.executeQuery();
			if(rs.next()){
				result = rs.getInt(1);
			}
			
			if(result > 0){
				return 1;
			}else{
				return 0; //登陆失败，用户名或密码有误
			}
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
			return -1;
		}catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}catch (Exception e) {
			e.printStackTrace();
			return -1;
		}finally {
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static int getMoney(String username){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/train?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String use = "root";
			String password = "123456";
			conn = DriverManager.getConnection(url, use, password);
			
			String sql = "select money from user where name=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			while(rs.next()){
				result = rs.getInt(1);
			}
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}	
