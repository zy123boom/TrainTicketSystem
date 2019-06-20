package boomzy.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import boomzy.dao.RegisterDao;
import boomzy.entity.User;

public class RegisterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//处理注册
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("userpwd");
		//添加session
		HttpSession session = request.getSession();
		session.setAttribute("username", username);
		session.setAttribute("userpwd", password);
		User user = new User(username,password);
		int result = RegisterDao.register(user);
		if(result >= 1 && (!username.equals("")) && (!password.equals(""))){
			request.getRequestDispatcher("successregister.jsp").forward(request, response);
		}else {
			response.sendRedirect("registerfail.jsp");
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
