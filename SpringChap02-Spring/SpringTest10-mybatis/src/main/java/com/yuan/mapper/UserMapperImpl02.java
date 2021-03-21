package com.yuan.mapper;

import com.yuan.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

public class UserMapperImpl02 extends SqlSessionDaoSupport implements UserMapper {
    public List<User> selectUser() {
        /*
        SqlSession sqlSession = getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.selectUser();
        */
        return getSqlSession().getMapper(UserMapper.class).selectUser();
    }
}
