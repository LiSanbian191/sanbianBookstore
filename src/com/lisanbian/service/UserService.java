package com.lisanbian.service;

import com.lisanbian.pojo.User;

public interface UserService {

    /**
     * 注册用户
     */
    void registerUser(User user);


    /**
     * 检查用户名是否可用
     */
    User login(User user);

    /**
     * 检查用户名是否存在
     * 返回true,表示用户名存在
     */
    boolean existsUsername(String username);





}
