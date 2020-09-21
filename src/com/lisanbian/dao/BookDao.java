package com.lisanbian.dao;

import com.lisanbian.pojo.Book;

import java.util.List;

public interface BookDao {
    int addBook(Book book);

    int deleteBookByID(Integer id);

    int updateBook(Book book);

    Book querryBookByID(Integer id);

    List<Book> querryBooks();


    Integer queryForPageTotalCount();

    List<Book> queryForPageItems(int begin, int pageSize);

    Integer queryForPageTotalCountByPrice(int min, int max);

    List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);
}
