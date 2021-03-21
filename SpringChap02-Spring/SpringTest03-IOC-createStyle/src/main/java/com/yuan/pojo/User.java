package com.yuan.pojo;

/**
 * Created by yuanlei on 2021/2/21.
 */
public class User {
    private String name;

    public User(String name) {
        this.name = name;
        System.out.println(name+" : name被创建");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void show(){
        System.out.println("name"+name);
    }
}
