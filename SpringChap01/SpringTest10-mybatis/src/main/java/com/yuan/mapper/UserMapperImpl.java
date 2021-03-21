package com.yuan.mapper;

import com.yuan.pojo.User;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

//其他方式：Mybatis-plus   通用Mapper

//Spring 接管对象 自动创建  mybatis无法自动创建  所以手动写一个set方法
public class UserMapperImpl implements UserMapper{

    //我们所有的操作，都哦是用sqlsession来执行，在原来
    //  现在都使用sqlseesionTemplate

    private SqlSessionTemplate sqlSession;

    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<User> selectUser(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.selectUser();
    }
}
