package boomzy.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import boomzy.dao.BuyTicketDao;
import boomzy.entity.Train;
import boomzy.entity.TrainNum;
public class BuyTicketServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String trainNum = request.getParameter("trainnumber");
		HttpSession session = request.getSession();
		session.setAttribute("trainNum", trainNum);
		TrainNum tnum = new TrainNum(trainNum);
		
		List<Train> result = BuyTicketDao.query(tnum);
		
		request.setAttribute("result",result);
		//存在数据，存储转发
		request.getRequestDispatcher("ticketmessage.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
