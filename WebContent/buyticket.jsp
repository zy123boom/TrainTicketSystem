<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Pragma" content="no-cache">

<title>火车票售票系统</title>
	 <style type="text/css">    
         body{    
            background-image: url(image/highspeed.jpg);    
            background-size:cover;  
         }    
     </style>
</head>
<body>
	<form action="BuyTicketServlet" method="post">
		请选择您要购买的车次：<input type="text" name="trainnumber"/>
		<input type="submit" value="购买"/>
	</form>
	
</body>
</html>