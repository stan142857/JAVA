package com.yuan.pojo2;

public class Client {
    public static void main(String [] args){
        UserServiceImpl userService = new UserServiceImpl();
        userService.add();
        //现行任务：为每一个操作增加日志 使用代理模式
        UserServiceProxy userServiceProxy = new UserServiceProxy();
        userServiceProxy.setUserService(userService);
        userServiceProxy.query();
        userServiceProxy.add();
    }
}
