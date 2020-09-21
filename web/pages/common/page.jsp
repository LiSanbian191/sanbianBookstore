<%@ page import="com.lisanbian.pojo.Book" %>
<%@ page import="com.lisanbian.pojo.Page" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: surface
  Date: 2020/9/3
  Time: 18:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="page_nav">

    <%if(bookPage.getPageNo()>1){%>
    <a href="${requestScope.page.url}&pageNo=1">首页</a>
    <a href="${requestScope.page.url}&pageNo=<%=bookPage.getPageNo()-1%>">上一页</a>
    <%}%>
    <a href="#">[<%=bookPage.getPageNo()%>]</a>
    <%if(bookPage.getPageNo()<bookPage.getPageCount()){%>
    <a href="${requestScope.page.url}&pageNo=<%=bookPage.getPageNo()+1%>">下一页</a>
    <a href="${requestScope.page.url}&pageNo=<%=(int)bookPage.getPageCount()%>">末页</a>
    <%}%>
    共<%=bookPage.getPageCount()%>页，<%=bookPage.getPageTotal()%>条记录 到第<input value="<%=bookPage.getPageCount()/2%>" name="pn" id="pn_input"/>页
    <input id="searchButton" type="button" value="确定"/>

</div>
</body>
</html>
