package com.yuan.pojo;

import lombok.Data;

@Data
public class User {
    private int id;
    private String username;
    private String userpwd;
    private String hobby;

    public User(int id, String username, String userpwd, String hobby) {
        this.id = id;
        this.username = username;
        this.userpwd = userpwd;
        this.hobby = hobby;
    }
}
