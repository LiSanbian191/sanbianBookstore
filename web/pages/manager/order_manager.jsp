<%@ page import="com.lisanbian.pojo.Order" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
	<%@include file="/pages/common/header.jsp"%>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">订单管理系统</span>
			<div>
				<a href="pages/manager/book_manager.jsp">图书管理</a>
				<a href="pages/manager/order_manager.jsp">订单管理</a>
				<a href="index.jsp">返回商城</a>
			</div>
	</div>
	
	<div id="main">
		<table>
			<%List<Order> orderList = (List<Order>)request.getAttribute("orders");%>
			<table>
				<tr>
					<td>编号</td>
					<td>日期</td>
					<td>金额</td>
					<td>状态</td>
					<td>详情</td>
				</tr>
				<%if(orderList!=null){%>
				<%String[] s = {"待发货","已发货","已签收"};%>
				<%for(Order order :orderList){%>
				<tr>
					<td><%=order.getOrder_id()%></td>
					<td><%=order.getCreate_time()%></td>
					<td><%=order.getTotal_money()%></td>
					<td><%=s[order.getStatus()]%></td>
					<td><a href="#">查看详情</a></td>
				</tr>
				<%}%>
				<%}%>
			</table>
		</table>
	</div>

	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>