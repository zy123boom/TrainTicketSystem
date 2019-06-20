package boomzy.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryAlreadyBuyDao {
	public static List<String> alreadyBuy(String user){
		List<String> tnos = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/train?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String use = "root";
			String password = "123456";
			conn = DriverManager.getConnection(url, use, password);
			
			String sql = "select tno from isbuy where user=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, user);
			rs = pstmt.executeQuery();
			while(rs.next()){
				String tno = rs.getString("tno");
				tnos.add(tno);
			}
			return tnos;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
