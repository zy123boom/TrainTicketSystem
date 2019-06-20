<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>购票系统登陆页面</title>
     <style type="text/css">    
         body{    
            background-image: url(image/highspeed.jpg);    
            background-size:cover;  
         }    
     </style>
</head>
<body>
	<center>
		
		<!-- 登陆功能 -->
		<form action="LoginServlet" method="post">
			用户名：<input type="text" name="username"/><br/>
			密码：<input type="password" name="userpwd"/><br/>
				<input type="submit" value="登陆"/>	
		</form>
		<!-- 注册功能 -->
		<form action="register.jsp">
			<input type="submit" value="注册" />
		</form>
	</center>
</body>
</html>