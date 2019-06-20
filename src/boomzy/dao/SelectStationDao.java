package boomzy.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import boomzy.entity.Station;
import boomzy.entity.Train;

public class SelectStationDao {
	public static List<Train> select(Station station){
		List<Train> trains = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Train train = null;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/train?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String use = "root";
			String password = "123456";
			conn = DriverManager.getConnection(url, use, password);
			
			String sql = "select * from traininformation where start like ? and end like ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+station.getStart()+"%");
			pstmt.setString(2, "%"+station.getEnd()+"%");
			
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
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch(Exception e){
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
