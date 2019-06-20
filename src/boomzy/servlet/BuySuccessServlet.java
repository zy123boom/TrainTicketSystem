package boomzy.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import boomzy.dao.BuyTicketDao;
import boomzy.entity.User;

public class BuySuccessServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		int money = (Integer) session.getAttribute("money");
		User user = new User(username, money);
		String trainNum = (String) session.getAttribute("trainNum");
		// 查看此人是否已经买了该车次
		int check = BuyTicketDao.isBuy(user, trainNum);
		if (check > 0) {
			request.getRequestDispatcher("alreadybuy.jsp").forward(request, response);			
		} else {
			// 查票
			int query = BuyTicketDao.queryTicket(trainNum);
			if (query <= 0) {
				response.sendRedirect("failbuy.jsp");
			}else{
				// 扣款
				// 第一步
				double priceresult = BuyTicketDao.getCostTno(trainNum);
				// 第二步
				int costresult = BuyTicketDao.cost(user, priceresult, trainNum);
				if (costresult <= 0) {
					request.getRequestDispatcher("moneyfailbuy.jsp").forward(request, response);
					// response.sendRedirect("moneyfailbuy.jsp");

				} else {
					// 买票
					int result = BuyTicketDao.buyTicket(trainNum, user);
					// 将买到票的人的信息放入数据库
					int flag = BuyTicketDao.putInBuy(user, trainNum);
					if (result > 0 && flag > 0) {
						request.getRequestDispatcher("successbuy.jsp").forward(request, response);
					}
				}
			}
			
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
