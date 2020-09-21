package com.lisanbian.web;

import com.lisanbian.pojo.Cart;
import com.lisanbian.pojo.Order;
import com.lisanbian.pojo.User;
import com.lisanbian.service.OrderService;
import com.lisanbian.service.impl.OrderServiceImpl;
import com.lisanbian.utlis.JdbcUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


public class OrderServlet extends BaseServlet{
    private OrderService orderService = new OrderServiceImpl();

    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user =(User) req.getSession().getAttribute("user");
        //验证用户登陆后才能结账
        if(user!=null){
            //获取购物车对象与userid
            Cart cart =(Cart) req.getSession().getAttribute("cart");
            Integer userId = user.getID();
            String orderId = null;
            //创建订单，返回订单号
            orderId = orderService.createOrder(cart, userId);

            //设置异常，测试业务是否回滚，遇到异常，业务回滚
            //int a =12/0;

            //重定向后request域无效，改为session保存
            //req.setAttribute("orderId",orderId);
            req.getSession().setAttribute("orderId",orderId);

            //刷新可能会引起重复提交
            //改为重定向
            //req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req,resp);

            resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
        }else {
            //跳转到登录界面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);

        }
    }

    protected void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orders = orderService.showAllOrders();
        req.setAttribute("orders",orders);
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req,resp);
    }
}

