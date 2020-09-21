package com.lisanbian.dao.impl;

import com.lisanbian.dao.BaseDao;
import com.lisanbian.dao.BookDao;
import com.lisanbian.pojo.Book;

import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public int addBook(Book book) {
        String sql = "insert into t_book (`name`,`author`,`price`,`sales`,`stock`,`img_path`) values(?,?,?,?,?,?)";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgpath());
    }

    @Override
    public int deleteBookByID(Integer id) {
        String sql = "delete from t_book where id = ?";
        return update(sql,id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set `name`=?,`author`=?,`price`=?,`sales`=?,`stock`=?,`img_path`=? where id = ?";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgpath(),book.getId());
    }

    @Override
    public Book querryBookByID(Integer id) {
        String sql = "select * from t_book where id = ?";
        Book book = queryForOne(sql,Book.class, id);
        return book;
    }

    @Override
    public List<Book> querryBooks() {
        String sql = "select * from t_book";
        return queryForList(sql,Book.class);
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from t_book";
        Number count  =(Number)queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
       String sql = "select * from t_book limit ?,?";
        List<Book> books = queryForList(sql,Book.class, begin, pageSize);
        return books;
    }

    @Override
    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max) {
        String sql = "select * from t_book where price between ? and ? order by price limit ?,?";
        List<Book> books = queryForList(sql,Book.class, min, max,begin,pageSize);
        return books;
    }

    @Override
    public Integer queryForPageTotalCountByPrice(int min, int max) {
        String sql = "select count(*) from t_book where price between ? and ? ";
        Number count = (Number)queryForSingleValue(sql,min,max);
        return count.intValue();
    }
}
