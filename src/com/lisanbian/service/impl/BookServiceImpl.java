package com.lisanbian.service.impl;

import com.lisanbian.dao.BookDao;
import com.lisanbian.dao.impl.BookDaoImpl;
import com.lisanbian.pojo.Book;
import com.lisanbian.pojo.Page;
import com.lisanbian.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    BookDao bookDao = new BookDaoImpl();

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookByID(Integer id) {
        bookDao.deleteBookByID(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookByID(Integer id) {
        return bookDao.querryBookByID(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.querryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<>();
        page.setPageNo(pageNo);
        page.getPageSize();

        //设置总记录数
        Integer pageTotal = bookDao.queryForPageTotalCount();
        page.setPageTotal(pageTotal);

        //求总页码
        Integer pageCount = pageTotal/pageSize;
        if(pageTotal%pageSize>0){
            pageCount+=1;
        }
        page.setPageCount(pageCount);
        //数据边界校验
       int pageno = pageNo;
        if(pageNo<1){
            pageno=1;
        }
        else if(pageNo>pageCount){
            pageno=pageCount;
        }

        page.setPageNo(pageno);



        int begin = (page.getPageNo()-1)*pageSize;

        List<Book> items = bookDao.queryForPageItems(begin,pageSize);
        page.setItems(items);

        return  page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page = new Page<>();
        page.setPageNo(pageNo);
        page.getPageSize();

        //设置总记录数
        Integer pageTotal = bookDao.queryForPageTotalCountByPrice(min,max);
        page.setPageTotal(pageTotal);

        //求总页码
        Integer pageCount = pageTotal/pageSize;
        if(pageTotal%pageSize>0){
            pageCount+=1;
        }
        page.setPageCount(pageCount);
        //数据边界校验
        int pageno = pageNo;
        if(pageNo<1){
            pageno=1;
        }
        else if(pageNo>pageCount){
            pageno=pageCount;
        }

        page.setPageNo(pageno);



        int begin = (page.getPageNo()-1)*pageSize;

        List<Book> items = bookDao.queryForPageItemsByPrice(begin,pageSize,min,max);
        page.setItems(items);

        return  page;
    }
}
