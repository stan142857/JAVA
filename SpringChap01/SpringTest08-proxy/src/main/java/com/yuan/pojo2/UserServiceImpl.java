package com.yuan.pojo2;

public class UserServiceImpl implements  UserService {

    public void add() {
        System.out.println("增加一个用户");
    }

    public void delete() {
        System.out.println("删除了一个用户");
    }

    public void update() {
        System.out.println("修改了一个用户");
    }

    public void query() {
        System.out.println("查询了一个用户");
    }
    //增加一个需求  为什么不能在原有代码上增加？
    //1. 改动原有的业务代码，在公司中属于大忌
}
