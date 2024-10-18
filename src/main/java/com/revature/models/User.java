package com.revature.models;

public class User {

    private int user_id;
    private String username;
    private String full_name;

    public User(int user_id, String username, String full_name){
        this.user_id = user_id;
        this.username = username;
        this.full_name = full_name;
    }

    public User(){
    }

    public User(String username, String full_name){
        this.username = username;
        this.full_name = full_name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", full_name='" + full_name + '\'' +
                '}';
    }
}
