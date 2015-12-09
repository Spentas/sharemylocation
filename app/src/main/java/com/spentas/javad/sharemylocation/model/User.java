package com.spentas.javad.sharemylocation.model;

/**
 * Created by javad on 12/8/2015.
 */
public class User {
    private String userId;
    private String Username;
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {

         this.phoneNumber = phoneNumber;

    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }
}
