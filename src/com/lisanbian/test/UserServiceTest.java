package com.lisanbian.test;

import com.lisanbian.pojo.User;
import com.lisanbian.service.UserService;
import com.lisanbian.service.impl.UserServiceImpl;
import org.junit.Test;


public class UserServiceTest {
    UserService userService = new UserServiceImpl();
    @Test
    public void registerUser() {
        userService.registerUser(new User(null,"李四","abc123","lisi@163.com"));
    }

    @Test
    public void login() {
        if(userService.login(new User(null,"李四","abc123",null))==null){
            System.out.println("登录失败!");
        }else {
            System.out.println("登录成功！");
        }
    }

    @Test
    public void existsUsername() {
        if(userService.existsUsername("张三")==true){
            System.out.println("用户名已存在");
        }else {
            System.out.println("用户名可用");
        }

    }
}