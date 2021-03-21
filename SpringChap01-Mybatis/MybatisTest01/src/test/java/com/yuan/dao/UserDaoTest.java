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
        //第一步 获取sqlsession对象
        SqlSession sqlSession = myBatisUtils.getSqlSession();
        //执行sql
        UserMapper sqlSessionMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = sqlSessionMapper.getUserList();
        for (User user:userList){
            System.out.print (user);
        }
//        关闭SqlSession
        sqlSession.close ();
    }
    @Test
    public void getUserById(){
        SqlSession sqlSession = myBatisUtils.getSqlSession();

        UserMapper sqlSessionMapper = sqlSession.getMapper(UserMapper.class);
        User user = sqlSessionMapper.getUserById(1);
        System.out.println(user);

        sqlSession.close();
    }
    @Test
    public void getUserlikeById(){
        SqlSession sqlSession = myBatisUtils.getSqlSession();

        UserMapper sqlSessionMapper = sqlSession.getMapper(UserMapper.class);

        List<User> userlikeById = sqlSessionMapper.getUserlikeById("1");
        for (User user:userlikeById){
            System.out.print (user);
        }

        sqlSession.close();
    }
    @Test
    public void insertUser(){
        SqlSession sqlSession = myBatisUtils.getSqlSession();

        UserMapper sqlSessionMapper = sqlSession.getMapper(UserMapper.class);
        int x = sqlSessionMapper.insertUser(new User("gly","gly","1"));
        if(x>=0){
            System.out.print("success");
        }
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void insertUser2(){
        SqlSession sqlSession = myBatisUtils.getSqlSession();
        UserMapper sqlSessionMapper = sqlSession.getMapper(UserMapper.class);
//      使用map进行插入操作
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("username","sxy");
        map.put("userpwd","sxy66666");
        sqlSessionMapper.insertUser2(map);

        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void updateUser(){
        SqlSession sqlSession = myBatisUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int x = mapper.updateUser(new User(21,"drgly","6654321","1"));
        if(x>=0){
            System.out.print("success");
        }
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void deleteUser(){
        SqlSession sqlSession = myBatisUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int x = mapper.deleteUser(21);
        if(x>=0){
            System.out.print("success");
        }
        sqlSession.commit();
        sqlSession.close();
    }
}
