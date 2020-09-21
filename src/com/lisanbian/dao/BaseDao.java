package com.lisanbian.dao;

import com.lisanbian.utlis.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {
    //使用dbutils操作数据库
    //首先导入包
    QueryRunner queryRunner = new QueryRunner();


    /**
     * update方法用来执行insert/update/delete操作
     * 若返回-1，则说明操作失败
     * 否则返回成功，且表示为受影响的行数
     */

    public int update(String sql,Object ...args){
        Connection conn = JdbcUtils.getConnection();
        try {
           return queryRunner.update(conn,sql,args);//返回受影响的行数
        } catch (SQLException e) {
            e.printStackTrace();
           throw new RuntimeException(e);
        }
    }


    /**
     * 查询一条记录，并返回javabean对象
     */

    public <T> T queryForOne(String sql,Class<T> clazz,Object ...args ){
        Connection conn = JdbcUtils.getConnection();

        try {
            return queryRunner.query(conn,sql,new BeanHandler<T>(clazz),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询一至多条条记录，并返回多个javabean对象
     */
    public <T>List<T> queryForList(String sql,Class<T> clazz, Object ...args){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new BeanListHandler<T>(clazz), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    /**
     * 查询返回单个值
     */
    public Object queryForSingleValue(String sql,Object ...args){
        Connection conn = JdbcUtils.getConnection();
        try {
          return   queryRunner.query(conn,sql,new ScalarHandler(),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }



}
