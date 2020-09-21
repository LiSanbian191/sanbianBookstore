package com.lisanbian.web;

import com.google.code.kaptcha.servlet.KaptchaServlet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lisanbian.pojo.User;
import com.lisanbian.service.UserService;
import com.lisanbian.service.impl.UserServiceImpl;
import com.lisanbian.utlis.WebUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;


public class UserServlet extends BaseServlet {
   private UserServiceImpl userService = new UserServiceImpl();
    //注册方法
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求信息
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        // 获取Session中的验证码
        String token = "12345";
                //(String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        // 删除 Session中的验证码
        //req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        System.out.println("服务器生成的验证码为："+token);

        User user = WebUtils.copyParametersToBean(req.getParameterMap(),new User());
        //2.检查验证码是否正确,先写死，后面用服务器生成
        if (token!=null&&token.equalsIgnoreCase(code)) {//忽略大小写
            //正确，继续向下执行
            //3.检查用户名是否可用
            if (userService.existsUsername(username)) {
                //不可用，返回注册页面
                req.setAttribute("msg", "用户名已存在！");
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
//                System.out.println("用户名已存在存在！");
            } else {
                //可用，保存用户信息，并跳转到注册成功页面
                userService.registerUser(user);
                System.out.println("注册成功");
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }
        } else {
            //验证码不正确返回注册页面
            req.setAttribute("msg", "验证码错误");
            req.setAttribute("username", username);
            req.setAttribute("email", email);
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);


        }
    }
    //登录方法
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           //1.获取请求参数
           String username = req.getParameter("username");
           String password = req.getParameter("password");

//           System.out.println(username);
//           System.out.println(password);


          //2.验证用户名和密码
        User user = userService.login(new User(null, username, password, null));

        if (user != null) {
            //用户名与密码正确，跳转到登录成功页面
            //保存用户登陆后的信息
            HttpSession session = req.getSession();
            session.setAttribute("user",user);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
//            System.out.println("登录成功！");
        } else {
            //用户名或密码错误
            //把错误信息，回显的表单项信息放进request域中
            req.setAttribute("msg", "用户名或密码错误");
            req.setAttribute("username", username);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);

        }
    }
    //登录方法
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         req.getSession().invalidate();
         resp.sendRedirect(req.getContextPath());

    }

    //用户名是否存在
    protected  void existsusername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        boolean existsUsername = userService.existsUsername(username);
        Gson gson = new Gson();
        Map<String,Object> usernameMap = new HashMap<>();
        usernameMap.put("username",existsUsername);
        String result = gson.toJson(usernameMap);

        //响应返回数据
        resp.getWriter().write(result);


    }
}
