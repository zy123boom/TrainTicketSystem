<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page language="java" import="boomzy.servlet.LoginServlet" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>火车售票系统</title>
	 <style type="text/css">    
         body{    
            background-image: url(image/highspeed.jpg);    
            background-size:cover;  
         }    
     </style>
</head>
<body>
	<center>欢迎您: <%
		String name = (String)session.getAttribute("username");
		if(name != null){
			out.print(name);
		}
		%>
		<br/>
		您的余额: <%
		int money = (Integer)session.getAttribute("money");
		out.print(money);
		%>
	</center>
	
	请选择出发站点和终到站点：<br/>
	若您想查看所有列车车次，请直接点击查询
	<form action="SelectStationServlet" method="get">
		出发站点：<input type="text" name="start"/><br/>
		终到站点：<input type="text" name="end"><br/>
			<input type="submit" value="查询"/>
	</form>
	<br/>
	<br/>
	<br/>
	
	<form action="QueryAlreadyBuyServlet" method="post">
		<input type="submit" value="查看已购车次" />
	</form>
	
	<a href="invalidate.jsp">注销</a>
	
</body>
</html>