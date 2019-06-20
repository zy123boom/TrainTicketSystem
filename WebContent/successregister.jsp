<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>火车票售票系统</title>
	 <style type="text/css">    
         body{    
            background-image: url(image/highspeed.jpg);    
            background-size:cover;  
         }    
     </style>
</head>
<body>
	注册成功，您的注册信息如下：<br/>
	<%
		String name = request.getParameter("username");
		String password = request.getParameter("userpwd");
		if(name != null){
			out.print("姓名： " + name + "<br/>");
		}
		if(password != null){
			out.print("密码" + password + "<br/>");
		}
	%>
	即将跳转到登陆页面，进行登陆...
	<meta http-equiv="Refresh" content="3;url= login.jsp">
</body>
</html>