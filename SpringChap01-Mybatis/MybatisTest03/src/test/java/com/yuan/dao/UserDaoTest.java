package com.yuan.dao;

import com.yuan.pojo.User;
import com.yuan.utils.myBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoTest {

    @Test
    public void test(){

        SqlSession sqlSession = myBatisUtils.getSqlSession();
//        测试是否从二级缓存读取
        SqlSession sqlSession2 = myBatisUtils.getSqlSession();
        UserMapper sqlSessionMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = sqlSessionMapper.getUserList();
        for (User user:userList){
            System.out.print (user);
        }
        sqlSession.close ();

//        测试是否从二级缓存读取
        UserMapper sqlSessionMapper2 = sqlSession2.getMapper(UserMapper.class);
        List<User> userList2 = sqlSessionMapper2.getUserList();
        for (User user:userList2){
            System.out.print (user);
        }
        sqlSession2.close ();
    }
}
