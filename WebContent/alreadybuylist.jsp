<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	 <style type="text/css">    
         body{    
            background-image: url(image/highspeed.jpg);    
            background-size:cover;  
         }    
     </style>
</head>
<body>
	<table border="1px">
		<tr>
			<th>车次</th>
			
		</tr>
		<%
			//获取SelectStationServlet中request域中的数据
			List<String> tnos = (List<String>)request.getAttribute("tnos"); 
		%>
		<%
			
			for(String tno : tnos){
		%>
		<tr>
			<td><%=tno%></td>
			
		</tr>
		<%
			}
		%>
	</table>
	
	<a href="selectStation.jsp">返回</a>
</body>
</html>