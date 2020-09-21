package com.lisanbian.test;

import com.lisanbian.pojo.Book;
import com.lisanbian.pojo.Page;
import com.lisanbian.service.BookService;
import com.lisanbian.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BookServiceTest {
    BookService bookService = new BookServiceImpl();

    @Test
    public void addBook() {
        bookService.addBook(new Book(null,"野草","鲁迅",new BigDecimal(23.5),14,190,"static/img/default.jpg"));
    }

    @Test
    public void deleteBookByID() {
        bookService.deleteBookByID(7);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(22,"野草","鲁迅",new BigDecimal(19.9),100,190,"static/img/default.jpg"));
    }

    @Test
    public void queryBookByID() {
        Book book = bookService.queryBookByID(18);
        System.out.println(book);
    }

    @Test
    public void queryBooks() {
        List<Book> books = bookService.queryBooks();
        for(Book book:books){
            System.out.println(book);
        }

    }
    @Test
    public void page(){
        BookService bookService = new BookServiceImpl();
        Page<Book> page = bookService.page(1, 5);
        System.out.println(page);
        for(Book book:page.getItems()){
            System.out.println(book);
        }

    }
    @Test
    public void pageByPrice(){
        BookService bookService = new BookServiceImpl();
        Page<Book> page = bookService.pageByPrice(0, 4,10,100);
        System.out.println(page);
        for(Book book:page.getItems()){
            System.out.println(book);
        }

    }
}