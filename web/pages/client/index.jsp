<%@ page import="java.util.List" %>
<%@ page import="com.lisanbian.pojo.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
	<%@include file="/pages/common/header.jsp"%>
	<script>
		//给加入购物车按钮绑定单击事件
     	$(function () {
			$("button.addToCart").click(function () {
				var bookid = $(this).attr("bookid");

				//location.href="http://localhost:8080/book/cartServlet?action=addItem&bookid="+bookid;
				$.getJSON("http://localhost:8080/book/cartServlet","action=ajaxAddItem&bookid="+bookid,function (data) {
					$(".cartTotalcount").text("  您的购物车中有【"+data.totalCount+"】本书");
					$(".lastBookName").text(data.lastBookName);
				})
			})
		});
	</script>
</head>
<body>
<div>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">网上书城</span>
			<div>

				<%Object user = session.getAttribute("user");%>

				<%if(user==null){%>
				<a href="pages/user/login.jsp">登录</a>
				<a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
				<%}%>
				<%if(user!=null){%>
				<span>欢迎<span style="color: #465c8b" class="um_span">${sessionScope.user.username}</span>光临三变书店</span>
				<a href="pages/order/order.jsp">我的订单</a>
				<a href="userServlet?action=logout">注销</a>&nbsp;&nbsp;
				<%}%>
				<a href="pages/cart/cart.jsp">购物车</a>
				<a href="pages/manager/manager.jsp">后台管理</a>
			</div>
	</div>
</div>
	<div id="main">

		<div id="book">
			<div class="book_cond">
				<form action="client/bookServlet" method="get">
					<input type="hidden" name ="action" value="pageByPrice">
					价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
						<input id="max" type="text" name="max" value="${param.max}"> 元
						<input type="submit" value="查询" />
				</form>
			</div>
			<div style="text-align: center">

				<%com.lisanbian.pojo.Cart cart =(Cart) session.getAttribute("cart");%>
				<%if(cart!=null&&!cart.getItems().isEmpty()){%>
				<span class="cartTotalcount" ></span>
				<div>
					&nbsp;&nbsp;您刚刚将《<span class="lastBookName" style="color: #465c8b"></span>》加入到了购物车中
				</div>
				<%}%>
				<%if(cart==null||cart.getItems().isEmpty()){%>
				<span class="cartTotalcount" style="color: #465c8b; text-align: center"> &nbsp;&nbsp; &nbsp;请将喜欢的书籍加入购物车吧!</span>
				<div>
					&nbsp;&nbsp;您刚刚将《<span class="lastBookName" style="color: #465c8b"> </span>》加入到了购物车中
				</div>
				<%}%>
			</div>

			<%Page<Book> bookPage =(Page<Book>)request.getAttribute("page"); %>
			<%List<Book> books =(List<Book>) bookPage.getItems();%>
			<%for(Book book:books) {%>
			<div class="b_list">

				<div class="img_div">
					<img class="book_img" alt="" src="static/img/default.jpg" />
				</div>
				<div class="book_info">
					<div class="book_name">
						<span class="sp1">书名:</span>
						<span class="sp2"><%=book.getName()%></span>
					</div>
					<div class="book_author">
						<span class="sp1">作者:</span>
						<span class="sp2"><%=book.getAuthor()%></span>
					</div>
					<div class="book_price">
						<span class="sp1">价格:</span>
						<span class="sp2">￥<%=book.getPrice()%></span>
					</div>
					<div class="book_sales">
						<span class="sp1">销量:</span>
						<span class="sp2"><%=book.getSales()%></span>
					</div>
					<div class="book_amount">
						<span class="sp1">库存:</span>
						<span class="sp2"><%=book.getStock()%></span>
					</div>
					<div class="book_add" >
						<button  bookid="<%=book.getId()%>" class="addToCart">加入购物车</button>
					</div>

				</div>

			</div>
			<%}%>

	    </div>
	<%@include file="/pages/common/page.jsp"%>
</div>

		<%@include file="/pages/common/footer.jsp"%>
</body>
</html>