<%@ page import="com.lisanbian.pojo.Cart" %>
<%@ page import="com.lisanbian.pojo.CartItem" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%@include file="/pages/common/header.jsp"%>
	<script src="js/jquery-1.7.2.js"></script>
	<script>

		$(function () {
			//删除书籍确认
			$("a.deleteItem").click(function () {
				return confirm("你确定要删除《"+$(this).parent().parent().find("td:first").text()+"》这本书吗？");
			});

			//清空购物车确认
			$("#clearCart").click(function () {
				return confirm("你确定要清空购物车吗？");
			});

			//内容发生改变时，调用
			$(".updateCount").change(function () {

				var bookname = $(this).parent().parent().find("td:first").text();
				var count  =this.value;
				var bookid = $(this).attr("bookid");
				if(confirm("你确定要将《"+bookname+"》的数量修改为："+count+"吗？")){
					location.href="http://localhost:8080/book/cartServlet?action=updateCount&bookid="+bookid+"&count="+count;
				}
				else {
					this.value = this.defaultValue;
				}

			});
		});

	</script>
</head>
<body>
	
	<div id="header" >
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
		<%@include file="/pages/common/login_success_welcome.jsp"%>
	</div>
	
	<div id="main" style="background-color:beige" >
		<% Cart cart = (Cart)request.getSession().getAttribute("cart");%>
		<%if(cart!=null&&!cart.getItems().isEmpty()){%>
		<%Set<Integer> integerSet =cart.getItems().keySet();%>
		<table>
			<tr>
				<td>书籍名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<%for(Integer integer:integerSet){%>

			<%CartItem cartItem = cart.getItems().get(integer);%>
			<tr>
				<td><%=cartItem.getName()%></td>
				<td>
					<input class="updateCount" style="width: 80px" type="text"  bookid="<%=cartItem.getId()%>" value="<%=cartItem.getCount()%>">
				</td>
				<td><%=cartItem.getPrice()%></td>
				<td><%=cartItem.getTotalPrice()%></td>
				<td><a class="deleteItem" href="cartServlet?action=deleteItem&bookid=<%=cartItem.getId()%>">删除</a></td>
			</tr>	
			<%}%>
		</table>

		<div class="cart_info">
			<span class="cart_span">购物车中共有<span class="b_count"><%=cart.getTotalCount()%></span>本书</span>
			<span class="cart_span">总金额<span class="b_price"><%=cart.getTotalPrice()%></span>元</span>
			<span class="cart_span"><a id="clearCart" href="cartServlet?action=clear">清空购物车</a></span>
			<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
		</div>
		<%}%>

		<%if(cart==null||cart.getItems().isEmpty()){%>
		<table >
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<tr>
				<td></td>
				<td></td>

				<td rowspan="5" colspan="3" ><a style="color:#465c8b" href="index.jsp" >这里空空如也，赶快添加你想要的书籍吧！</a></td>
				<td></td>
				<td></td>
			</tr>
		</table>
		<%}%>
	</div>

	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>