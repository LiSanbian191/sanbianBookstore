<%@ page import="com.lisanbian.pojo.Order" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
	<%@include file="/pages/common/header.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">我的订单</span>
		<%@include file="/pages/common/login_success_welcome.jsp"%>
	</div>
	
	<div id="main">
		<%List<Order> orderList = Order.getAttribute("orders");%>


		<table>
			<tr>
				<td>编号</td>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情</td>
			</tr>
			<%if(orderList!=null){%>
			<%for(Order order :orderList){%>
			<tr>
				<td><%=order.getOrder_id()%></td>
				<td><%=order.getTotal_money()%></td>
				<%String[] s = {"待发货","已发货","已签收"};%>
				<td><%=s[order.getStatus()]%></td>
				<td><a href="#">查看详情</a></td>
			</tr>
			<%}%>
			<%}%>
		</table>
		
	
	</div>

	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>