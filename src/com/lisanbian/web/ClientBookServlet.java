package com.lisanbian.web;

import com.lisanbian.pojo.Book;
import com.lisanbian.pojo.Page;
import com.lisanbian.service.BookService;
import com.lisanbian.service.impl.BookServiceImpl;
import com.lisanbian.utlis.WebUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ClientBookServlet extends BaseServlet{
    private BookService bookService = new BookServiceImpl();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求参数，pageNo,pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        //2.调用bookService.page(pageNo,pageSize),返回page对象
        Page<Book> page = bookService.page(pageNo,pageSize);
        page.setUrl("client/bookServlet?action=page");

        //3.保存page对象到request域中
        req.setAttribute("page",page);

        //4.请求跳转到/pages/manager/book_manager.jsp中
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);

    }

    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求参数，pageNo,pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //获取价格区间
        int min = WebUtils.parseInt(req.getParameter("min"),0);
        int max = WebUtils.parseInt(req.getParameter("max"),Integer.MAX_VALUE);

        //解决分页时价格区间变动BUG
        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");

        if(req.getParameter("min")!=null){
            sb.append("&min=").append(req.getParameter("min"));
        }
        if(req.getParameter("max")!=null){
            sb.append("&max=").append(req.getParameter("max"));
        }

        //2.调用bookService.page(pageNo,pageSize),返回page对象
        Page<Book> page = bookService.pageByPrice(pageNo,pageSize,min,max);
        page.setUrl(sb.toString());

        //3.保存page对象到request域中
        req.setAttribute("page",page);


        //4.请求跳转到/pages/manager/book_manager.jsp中
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);

    }
}

