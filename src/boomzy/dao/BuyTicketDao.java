package boomzy.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

import boomzy.entity.Train;
import boomzy.entity.TrainNum;
import boomzy.entity.User;

public class BuyTicketDao {
	public static List<Train> query(TrainNum tnum) {
		List<Train> trains = new ArrayList<>();
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		Train train = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/train?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String use = "root";
			String password = "123456";
			conn = DriverManager.getConnection(url, use, password);
			
			String sql = "select * from traininformation where tno = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, tnum.getTrainNum());
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				String tno = rs.getString("tno");
				String start = rs.getString("start");
				String end = rs.getString("end");
				String trainType = rs.getString("traintype");
				double startTime = rs.getDouble("starttime");
				double endTime = rs.getDouble("endtime");
				double price = rs.getDouble("price");
				int ticketNum = rs.getInt("ticketnum");
				train = new Train(tno, start, end, trainType, startTime, endTime, price, ticketNum);
				trains.add(train);
			}
			return trains;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//查票
	public static int queryTicket(String tnum){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int queryResult = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/train?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String use = "root";
			String password = "123456";
			conn = DriverManager.getConnection(url, use, password);
			
			String sql = "select ticketnum from traininformation where tno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tnum);
			rs = pstmt.executeQuery();
			while(rs.next()){
				queryResult = rs.getInt(1);
			}
			return queryResult;
		}catch (Exception e) {
			e.printStackTrace();
			return -1;
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) rs.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//扣款第一步：先得到车次对应的价格
	public static double getCostTno(String tnum){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		double price = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/train?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String use = "root";
			String password = "123456";
			conn = DriverManager.getConnection(url, use, password);
			
			String sql = "select price from traininformation where tno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tnum);
			rs = pstmt.executeQuery();
			while(rs.next()){
				price = rs.getDouble(1);
			}
			
			return price;
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//扣款第二步
	public static int cost(User user, double price, String tnum){
		Connection conn = null;
		PreparedStatement pstmt = null;
//		price = getCostTno(tnum);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/train?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String use = "root";
			String password = "123456";
			conn = DriverManager.getConnection(url, use, password);
			conn.setAutoCommit(false);
			String sql = "update user set money=money-"+price+" where name=? and money-"+price+">0";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			
			int result = pstmt.executeUpdate();
			conn.commit();
			if(result < 1){
				conn.rollback();
			}

			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return 0;
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	

	//买到车票
	public static int buyTicket(String tnum, User user){
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/train?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String use = "root";
			String password = "123456";
			conn = DriverManager.getConnection(url, use, password);			
			conn.setAutoCommit(false);
			String sql = "update traininformation set ticketnum=ticketnum-1 where ticketnum>0 and tno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tnum);
			
			int update = pstmt.executeUpdate();
			conn.commit();
			if(update < 1){
				conn.rollback();
			}
			return update;
			
		}catch (Exception e) {
			e.printStackTrace();
			return -2;
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//将买过的人放进买票的名单
	public static int putInBuy(User user, String tnum){
		Connection conn = null;
		PreparedStatement pstmt = null;
		int res = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/train?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String use = "root";
			String password = "123456";
			conn = DriverManager.getConnection(url, use, password);
			
			String sql = "insert into isbuy values(?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, tnum);
			
			res = pstmt.executeUpdate();
			return res;
					
		}catch (Exception e) {
			e.printStackTrace();
			return -1;
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//是否已经买过
	public static int isBuy(User user, String tnum){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int res = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/train?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String use = "root";
			String password = "123456";
			conn = DriverManager.getConnection(url, use, password);
			
			String sql = "select count(*) from isbuy where user=? and tno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, tnum);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				res = rs.getInt(1);
			}
			return res;
					
		}catch (Exception e) {
			e.printStackTrace();
			return -1;
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
