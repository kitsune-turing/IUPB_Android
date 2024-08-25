package com.kitsune.android.u.auth.Model;


import android.content.Context;
import com.kitsune.android.u.auth.Configuration.DataBase;


public class User {
    private String email;
    private String pass;
    private DataBase db;

    public User(Context context) {
        this.db = new DataBase(context);
    }

    public void verifyCredentials(String email, String password) {
        db.verifyUser(email, password);
    }
}