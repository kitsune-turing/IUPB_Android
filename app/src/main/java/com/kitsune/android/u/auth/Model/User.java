package com.kitsune.android.u.auth.Model;

import com.kitsune.android.u.auth.Configuration.DataBase;

public class User {
    private String email;
    private String password;
    private DataBase db;

    public User() {
        this.db = new DataBase();
    }

    public boolean verifyCredentials(String email, String password) {
        return db.verifyUser(email, password);
    }
}