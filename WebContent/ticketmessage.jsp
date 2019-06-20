<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="boomzy.entity.Train" %>
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
	您要买的列车信息如下：<br/>
	<table border="1px">
		<tr>
			<th>车次</th>
			<th>始发站</th>
			<th>终到站</th>
			<th>列车类型</th>
			<th>始发时间</th>
			<th>终到时间</th>
			<th>价格</th>	
			<th>余票</th>
		</tr>
		<%
			//获取BuyTicketServlet中request域中的数据
			List<Train> result = (List<Train>)request.getAttribute("result"); 
		%>
		<%
			
			for(Train train : result){
		%>
		<tr>
			<td><%=train.getTno()%></td>
			<td><%=train.getStart()%></td>
			<td><%=train.getEnd()%></td>
			<td><%=train.getTrainType()%></td>
			<td><%=train.getStartTime()%></td>
			<td><%=train.getEndTime()%></td>
			<td><%=train.getPrice()%></td>
			<td><%=train.getTicketNum()%></td>
		</tr>
		<%
			}
		%>
	</table>
	确认购买吗：<br/>
	<form action="BuySuccessServlet">
		<input type="submit" value="是"/>
	</form>	
	<form action="selectStation.jsp">
		<input type="submit" value="否"/>
	</form>
	
</body>
</html>