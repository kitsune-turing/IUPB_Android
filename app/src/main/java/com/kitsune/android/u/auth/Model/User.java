package com.kitsune.android.u.auth.Model;


public class User {
    private String uid;
    private String fullName;
    private String email;
    private String phone;

    public User() { }

    public User(String uid, String fullName, String email, String phone) {
        this.uid = uid;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}