package boomzy.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import boomzy.entity.User;

public class RegisterDao {
	//注册功能
	public static int register(User user){
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/train?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String use = "root";
			String password = "123456";
			conn = DriverManager.getConnection(url, use, password);
			String sql = "insert into user values(?,?,?)";		
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setInt(3, 2000);
			int count = pstmt.executeUpdate();
			if(count > 0){
				return 1;
			} else{
				return 0;  //添加失败，重复
			}
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
			return -1;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		} finally {
			try{
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
