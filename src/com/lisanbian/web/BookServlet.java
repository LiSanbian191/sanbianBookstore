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
import java.util.List;

public class BookServlet extends BaseServlet {
    BookService bookService = new BookServiceImpl();

    /**
     * 图书管理之添加图书
     */
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数，封装成对象
        Book book = WebUtils.copyParametersToBean(req.getParameterMap(), new Book());

        //添加图书
        bookService.addBook(book);

        //添加后使其跳转到到最后一页显示
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),0);
        pageNo+=1;



        //页面跳转,有bug
        //req.getRequestDispatcher("/manager/bookServlet?action=list").forward(req,resp);
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo="+pageNo);

    }

    /**
     * 图书管理之删除图书
     */
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        bookService.deleteBookByID(id);
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),0);
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo="+pageNo);
    }

    /**
     * 图书管理部分的图书信息修改
     */
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取要修改的图书信息
        Book book = WebUtils.copyParametersToBean(req.getParameterMap(), new Book());
        //2.封装成Book对象，调用方法修改图书
        bookService.updateBook(book);
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),0);

        //3.返回图书管理列表
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo="+pageNo);
    }

    /**
     * 图书管理之图书列表展示
     */
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.通过BookService查询全部图书信息
        List<Book> books = bookService.queryBooks();

        //2.将结果保存到request域中
        req.setAttribute("books", books);

        //3.请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);

    }

    /**
     * 修改图书时将信息带到修改页面
     */
    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取修改Book的id
        int id = WebUtils.parseInt(req.getParameter("id"), 0);

        //2.根据id查询到图书信息
        Book book = bookService.queryBookByID(id);

        //3.将Book保存到request域中
        req.setAttribute("book",book);

        //4.跳转到图书编辑页面
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }

    /**
     * 处理分页功能
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求参数，pageNo,pageSize
         int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
         int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        //2.调用bookService.page(pageNo,pageSize),返回page对象
        Page<Book> page = bookService.page(pageNo,pageSize);
        page.setUrl("manager/bookServlet?action=page");
        //3.保存page对象到request域中
        req.setAttribute("page",page);

        //4.请求跳转到/pages/manager/book_manager.jsp中
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);

    }
}