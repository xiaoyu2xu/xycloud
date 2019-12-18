package com.xy.vedio;

/**
 * Created by asus on 2019/8/3.
 */
public class Test1 {
    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Test1(){

    }

    public Test1(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
