<%@ page import="com.lisanbian.pojo.Book" %>
<%@ page import="java.util.*" %>
<%@ page import="com.lisanbian.pojo.Page" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
	<%@include file="/pages/common/header.jsp"%>
	<%Page<Book> bookPage =(Page<Book>) request.getAttribute("page"); %>
	<script>
		$(function () {
			$("a.deleteClass").click(function (){
		       return confirm("你确定要删除"+$(this).parent().parent().find("td:first").text()+"这本书吗？");
			})
		});

	</script>
	<script>
		$(function () {
			$("#searchButton").click(function () {
				var pageNo = $("#pn_input").val();//获取输入框跳转页码的值
				//JS地址栏中提供了一个地址栏对象location,href属性可获取地址栏中的地址
				//href属性地址可读可写，跳到指定地址

				//进行输入的边界校验


				if(pageNo<1)
				{
					location.href="<%=pageContext.getAttribute("basePath")%>${requestScope.page.url}&pageNo=1";
				}

				else if(pageNo><%=bookPage.getPageCount()%>){
					location.href="<%=pageContext.getAttribute("basePath")%>${requestScope.page.url}&pageNo="+<%=bookPage.getPageCount()%>;
				}
				else {
					location.href="<%=pageContext.getAttribute("basePath")%>${requestScope.page.url}&pageNo="+pageNo;
				}
			})
		});
	</script>
</head>
<body>

	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>
			<div>
				<a href="pages/manager/book_manager.jsp">图书管理</a>
				<a href="pages/manager/order_manager.jsp">订单管理</a>
				<a href="index.jsp">返回商城</a>
			</div>
	</div>
	
	<div id="main">
		<table>


			<%List<Book> books =(List<Book>) bookPage.getItems();%>
				<%for(Book book:books) {%>
			<tr>
				<td><%=book.getName()%></td>
				<td><%=book.getPrice()%></td>
				<td><%=book.getAuthor()%></td>
				<td><%=book.getSales()%></td>
				<td><%=book.getStock()%></td>

				<td colspan="2"><a href="manager/bookServlet?action=getBook&id=<%=book.getId()%>&pageNo=<%=bookPage.getPageNo()%>">修改</a></td>
                <td colsapn="2"><a class="deleteClass" href="manager/bookServlet?action=delete&id=<%=book.getId()%>&pageNo=<%=bookPage.getPageNo()%>">删除</a></td>
			</tr>
			<%}%>
			
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>

				<td><a href="pages/manager/book_edit.jsp?pageNo=<%=bookPage.getPageCount()%>">添加</a></td>
			</tr>	
		</table>

		<%@include file="/pages/common/page.jsp"%>

	</div>
	<%@include file="/pages/common/footer.jsp"%>


</body>
</html>