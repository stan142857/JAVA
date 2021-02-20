package com.yuan.dao;

import com.yuan.pojo.User;
import java.util.List;
import java.util.Map;

/**
 * Created by yuanlei on 2021/2/14.
 */
public interface UserMapper {
    //查询全部用户
    List<User> getUserList();

    //根据ID查询用户
    User getUserById(int id);

    //根据ID查询用户  -  like
    List<User> getUserlikeById(String id);

    //插入用户
    int insertUser(User user);

    //MAP
    int insertUser2(Map<String,Object> map);

    //修改用户
    int updateUser(User user);

    //删除用户
    int deleteUser(int id);

}
