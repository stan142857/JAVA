package com.yuan.mapper;

import com.yuan.pojo.User;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

public class UserMapperImpl extends SqlSessionDaoSupport implements UserMapper {
    public List<User> selectUser() {

        User user = new User(6,"stand", "stands", "5");
        UserMapper userMapper = getSqlSession().getMapper(UserMapper.class);

        userMapper.addUser(user);
        userMapper.deleteUser(6);

        return userMapper.selectUser();
    }

    public int addUser(User user) {
        return getSqlSession().getMapper(UserMapper.class).addUser(user);
    }

    public int deleteUser(int id) {
        return getSqlSession().getMapper(UserMapper.class).deleteUser(id);
    }
}
