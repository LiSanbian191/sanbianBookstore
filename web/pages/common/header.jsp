<%@ page import="java.util.Enumeration" %>
<%@ page import="java.security.Principal" %>
<%@ page import="java.io.IOException" %>
<%@ page import="jakarta.servlet.http.*" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.io.UnsupportedEncodingException" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.util.Locale" %>
<%@ page import="jakarta.servlet.*" %><%--
  Created by IntelliJ IDEA.
  User: surface
  Date: 2020/8/28
  Time: 9:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>

<body>
<%--静态包含base标签--%>
<%
String basePath = request.getScheme()
        +"://"
        +request.getServerName()
        +":"
        +request.getServerPort()
        +request.getContextPath()
        +"/";
pageContext.setAttribute("basePath",basePath);
%>
<base href="<%=basePath%>">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script src="js/jquery-1.7.2.js"></script>
</body>
</html>
