package boomzy.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boomzy.dao.SelectStationDao;
import boomzy.entity.Station;
import boomzy.entity.Train;

public class SelectStationServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		
		Station station = new Station(start, end);
		
		List<Train> trains = SelectStationDao.select(station);
		request.setAttribute("trains", trains);
		//存在数据，存储转发
		request.getRequestDispatcher("selectstationsuccess.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
