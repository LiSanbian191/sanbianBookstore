package com.lisanbian.dao;

import com.lisanbian.pojo.User;

public interface UserDao {
    /**
     * 根据用户名查询用户信息
     * 如果返回null,则说明数据库中未有此用户
     */
    User queryUserByUsername(String username);

    /**
     * 保存用户信息
     * 返回-1，表示操作失败
     */
    int saveUser(User user);


    /**
     * 根据用户名和密码查询用户，若返回null,则说明用户名或密码错误
     */

    User queryUserByUsernameAndPassword(String username,String password);


}
