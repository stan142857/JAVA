package com.yuan.pojo;

import java.util.Date;

/**
 * Created by yuanlei on 2021/2/14.
 */
public class User {
    private int id;
    private String username;
    private String userpwd;
    private String hobby;

    public User() {
    }
//    插入用户使用
    public User(String username, String userpwd, String hobby) {
        this.username = username;
        this.userpwd = userpwd;
        this.hobby = hobby;
    }

    public User(int id, String username, String userpwd, String hobby) {
        this.id = id;
        this.username = username;
        this.userpwd = userpwd;
        this.hobby = hobby;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", userpwd='" + userpwd + '\'' +
                ", hobby='" + hobby +
                '}';
    }
}
