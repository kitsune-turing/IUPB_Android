package com.kitsune.android.u.auth;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


public class Register extends Activity {
    private TextView loginButton;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_register);

        loginButton = findViewById(R.id.btn_sign_in_prompt);
        loginButton.setOnClickListener(view -> startActivity(new Intent(Register.this, Login.class)));
    }
}