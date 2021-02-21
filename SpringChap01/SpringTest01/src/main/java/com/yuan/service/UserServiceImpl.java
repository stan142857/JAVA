package com.yuan.service;

import com.yuan.dao.UserDao;
import com.yuan.dao.UserDaoImpl;

/**
 * Created by yuanlei on 2021/2/20.
 */
public class UserServiceImpl implements UserService {

    //程序控制选择的对象，但是会导致后期大量修改
    //private UserDao userDao = new UserDaoImpl();
    //优化方案：
    //设置接口，用set方法进行动态实现值的注入
    private UserDao userDao ;
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void getUser() {
        userDao.getUser();
    }
}
