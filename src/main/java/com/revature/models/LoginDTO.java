package com.revature.models;

//DTO (Data Transfer Object) meant to model some data that doesn't pertain to a DB table
// Allows a user to login with just username/password rather than whole object.
// Not stored in db, used solely for DATA TRANSFER
public class LoginDTO {


    private int user_id;
    private String username;

    public LoginDTO(){

    }

    public LoginDTO(int user_id, String username){
        this.user_id = user_id;
        this.username = username;
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

    @Override
    public String toString() {
        return "LoginDTO{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                '}';
    }
}
