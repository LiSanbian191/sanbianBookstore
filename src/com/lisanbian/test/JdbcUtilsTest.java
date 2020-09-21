package com.lisanbian.test;

import com.lisanbian.utlis.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcUtilsTest {
    @Test
    public void testJdbcUtils() throws SQLException {
        Connection conn = JdbcUtils.getConnection();
        System.out.println(conn);
        conn.setAutoCommit(false);
        //获取之后一定要关闭(还回连接池)
        //若有修改默认设置，也一并改回
        conn.setAutoCommit(true);

    }



}
