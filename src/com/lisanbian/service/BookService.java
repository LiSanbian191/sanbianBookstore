package com.lisanbian.service;

import com.lisanbian.pojo.Book;
import com.lisanbian.pojo.Page;

import java.util.List;

public interface BookService {
     void addBook(Book book);

     void deleteBookByID(Integer id);

     void updateBook(Book book);

     Book queryBookByID(Integer id);

     List<Book> queryBooks();

    Page<Book> page(int pageNo, int pageSize);

    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
