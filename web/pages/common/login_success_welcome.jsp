<%--
  Created by IntelliJ IDEA.
  User: surface
  Date: 2020/8/28
  Time: 8:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <span>欢迎<span style="color: #465c8b" class="um_span">${sessionScope.user.username}</span>光临三变书城</span>
    <a href="pages/order/order.jsp">我的订单</a>
    <a href="userServlet?action=logout?action=logout">注销</a>&nbsp;&nbsp;
    <a href="index.jsp">返回</a>

</div>
</body>
</html>
