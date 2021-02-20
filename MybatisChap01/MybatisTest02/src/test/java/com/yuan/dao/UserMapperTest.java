package com.yuan.dao;

import com.yuan.pojo.User;
import com.yuan.utils.myBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * Created by yuanlei on 2021/2/15.
 */
public class UserMapperTest {
    @Test
    public void test(){
        SqlSession sqlSession = myBatisUtils.getSqlSession();
        //注解 底层应用原理：动态代理
        //本质：反射机制
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = mapper.getUserList();
        for (User user:userList){
            System.out.print(user);
        }

        sqlSession.close();
    }
}
