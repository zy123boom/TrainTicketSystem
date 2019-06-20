package boomzy.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import boomzy.dao.LoginDao;
import boomzy.entity.User;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//处理登陆
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("userpwd");
		int money = LoginDao.getMoney(username);		
		//添加session
		HttpSession session = request.getSession();
		session.setAttribute("username",username);
		session.setAttribute("userpwd", password);
		session.setAttribute("money", money);
		User user = new User(username, password);
		int result = LoginDao.login(user);
		//是否登陆成功
		if(result >= 1 && (!username.equals("")) && (!password.equals(""))){
			
			request.getRequestDispatcher("selectStation.jsp").forward(request, response);
		}else if(result == 0){			
			response.sendRedirect("loginfail.jsp");
		}else{
			response.sendRedirect("error.jsp");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
