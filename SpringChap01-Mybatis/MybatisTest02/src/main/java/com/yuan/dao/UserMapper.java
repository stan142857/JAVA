package com.yuan.dao;

import com.yuan.pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by yuanlei on 2021/2/14.
 */
public interface UserMapper {
    //查询全部用户
    //使用注解开发  -- 可以用于简单sql的开发
    @Select("select * from infomanage.userinfo")
    List<User> getUserList();

}
