package com.lisanbian.filter;

import com.lisanbian.utlis.JdbcUtils;
import jakarta.servlet.*;

import java.io.IOException;


public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest,servletResponse);
            JdbcUtils.commitAndClose();//提交事务
        }catch (Exception e){
            JdbcUtils.rollbackAndClose();//回滚事务
            e.printStackTrace();

            throw new RuntimeException(e);//继续抛出异常，交给服务器进行错误页面展示
        }

    }
}
