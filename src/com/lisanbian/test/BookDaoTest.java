package com.lisanbian.test;

import com.lisanbian.dao.BookDao;
import com.lisanbian.dao.impl.BookDaoImpl;
import com.lisanbian.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTest {
    BookDao bookDao = new BookDaoImpl();
    @Test
    public void addBook() {

        Book book = new Book(null,"论语","孔子",new BigDecimal(100),10,1000,"static/img/default.jpg");
        bookDao.addBook(book);
    }

    @Test
    public void deleteBookByID() {
        bookDao.deleteBookByID(6);
    }

    @Test
    public void updateBook() {
        Book book = new Book(21,"春秋","孔子",new BigDecimal(1000.00),10,1000,"static/img/default.jpg");
        bookDao.updateBook(book);
    }

    @Test
    public void querryBookByID() {
        Book book = bookDao.querryBookByID(20);
        System.out.println(book);

    }

    @Test
    public void querryBooks() {
        List<Book> books = bookDao.querryBooks();
        for(Book book:books){
            System.out.println(book);
        }
    }

    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount());
    }

    @Test
    public void  queryForPageItems() {
        List<Book> books = bookDao.queryForPageItems(10, 5);
        for(Book book:books){
            System.out.println(book);
        }
        System.out.println(books.size());
    }
    @Test
    public void queryForPageTotalCountByPrice() {
        System.out.println(bookDao.queryForPageTotalCountByPrice(10,10000));
    }

    @Test
    public void  queryForPageItemsByPrice() {
        List<Book> books = bookDao.queryForPageItemsByPrice(2, 4,10,100);
        for(Book book:books){
            System.out.println(book);
        }
        System.out.println(books.size());
    }
}