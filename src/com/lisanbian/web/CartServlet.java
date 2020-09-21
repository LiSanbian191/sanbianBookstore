package com.lisanbian.web;

import com.google.gson.Gson;
import com.lisanbian.pojo.Book;
import com.lisanbian.pojo.Cart;
import com.lisanbian.pojo.CartItem;
import com.lisanbian.service.BookService;
import com.lisanbian.service.impl.BookServiceImpl;
import com.lisanbian.utlis.WebUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet {

    /**
     * 加入购物车
     */
    /*protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("加入购物车");
//        System.out.println("商品编号="+req.getParameter("bookid"));

        //获取请求参数，商品id
        int id = WebUtils.parseInt(req.getParameter("bookid"),0);

        //调用方法，查询图书信息
        BookService bookService = new BookServiceImpl();
        Book book = bookService.queryBookByID(id);

        //把图书信息转换为CaryItem商品项
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice());

        //调用Cart.add()方法，加入到购物车
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if(cart==null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);

        //将最后一个添加的商品保存到session域中，好在主页面显示添加的信息
        req.getSession().setAttribute("lastBook",cartItem.getName());

        //重定向回商品页面,req.getHeader("Referer")获取地址栏中的信息，返回该页面
        resp.sendRedirect(req.getHeader("Referer"));

    }*/

    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数，商品id
        int id = WebUtils.parseInt(req.getParameter("bookid"),0);

        //调用方法，查询图书信息
        BookService bookService = new BookServiceImpl();
        Book book = bookService.queryBookByID(id);

        //把图书信息转换为CaryItem商品项
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice());

        //调用Cart.add()方法，加入到购物车
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if(cart==null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);


        Map<String,Object> result = new HashMap<>();
        result.put("totalCount",cart.getTotalCount());
        result.put("lastBookName",cartItem.getName());
        Gson gson = new Gson();
        String json = gson.toJson(result);

        resp.getWriter().write(json);

    }

    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart =(Cart) req.getSession().getAttribute("cart");
        cart.clear();
        resp.sendRedirect(req.getContextPath()+"/pages/cart/cart.jsp");
    }

    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("bookid"),0);
        Cart cart =(Cart) req.getSession().getAttribute("cart");

        if(cart!=null){
            cart.deleteItem(id);
        }
        resp.sendRedirect(req.getContextPath()+"/pages/cart/cart.jsp");

    }
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("bookid"),0);
        int count  = WebUtils.parseInt(req.getParameter("count"),1);
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if(cart!=null){
            cart.updateItem(id,count);
        }
        resp.sendRedirect(req.getContextPath()+"/pages/cart/cart.jsp");

    }


}
