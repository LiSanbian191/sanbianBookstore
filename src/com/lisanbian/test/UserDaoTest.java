package com.lisanbian.test;

import com.lisanbian.dao.UserDao;
import com.lisanbian.dao.impl.UserDaoImpl;
import com.lisanbian.pojo.User;
import org.junit.Test;


public class UserDaoTest {
    //创建实现类对象
    UserDao userDao = new UserDaoImpl();
    @Test
    public void queryUserByUsername() {

        //System.out.println(userDao.queryUserByUsername("admin"));
        if(userDao.queryUserByUsername("admin")==null){
            System.out.println("该用户名可用！");
        }else {
            System.out.println("用户名已存在！");
        }
    }

    @Test
    public void saveUser() {
       if(userDao.saveUser(new User(0,"root","123456","admin@163.com"))!=-1){
           System.out.println("用户信息保存成功！");
       }else {
           System.out.println("用户信息保存失败！");
       }

    }

    @Test
    public void queryUserByUsernameAndPassword() {
        if(userDao.queryUserByUsernameAndPassword("admin","admin")==null){
            System.out.println("用户名或密码不正确");
        }else {
            System.out.println("登录成功");
        }
    }
}