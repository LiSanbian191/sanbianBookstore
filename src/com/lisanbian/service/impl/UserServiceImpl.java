package com.lisanbian.service.impl;

import com.lisanbian.dao.UserDao;
import com.lisanbian.dao.impl.UserDaoImpl;
import com.lisanbian.pojo.User;
import com.lisanbian.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();
    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        if(userDao.queryUserByUsername(username)==null) {
            return false;
        }else{
            return true;
        }
    }
}
