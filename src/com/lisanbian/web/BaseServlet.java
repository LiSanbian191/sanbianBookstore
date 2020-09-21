package com.lisanbian.web;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doPost(req,resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取action内容
        String action = req.getParameter("action");

        try {
            //使用反射优化代码，避免使用过多的if/else判断
                Method declaredMethod ;
                declaredMethod = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
                declaredMethod.invoke(this,req,resp);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);//抛出异常，交给过滤器处理
            }
        }
}






