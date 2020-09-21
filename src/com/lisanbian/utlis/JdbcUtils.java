package com.lisanbian.utlis;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
    
    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();

    static {
        try {
            Properties properties = new Properties(); // 读取 jdbc.properties 属性配置文件
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //从流中加载数据
            properties.load(inputStream);
            //创建数据库连接 池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
            }
        catch(Exception e) {
            e.printStackTrace();
           }
           }

          /** 获取数据库连接池中的连接
           * @return 如果返回 null,说明获取连接失败<br/>有值就是获取连接成功
           * */
        public static Connection getConnection() {
            Connection conn = connectionThreadLocal.get();
            if(conn == null){
                try {
                    conn = dataSource.getConnection();
                    //设置为手动提交
                    conn.setAutoCommit(false);
                    connectionThreadLocal.set(conn);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return conn;
        }

        //提交事务及关闭连接
        public static void commitAndClose(){
            Connection conn = connectionThreadLocal.get();
            if(conn!=null){
                try {
                    conn.commit();
                    conn.setAutoCommit(true);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                finally {
                    try {
                        conn.close();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
            //一定不能忘
            connectionThreadLocal.remove();
        }

    public static void rollbackAndClose(){
        Connection conn = connectionThreadLocal.get();
        if(conn!=null){
            try {
                conn.rollback();
                conn.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            finally {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        //一定不能忘
        connectionThreadLocal.remove();
    }

    }

