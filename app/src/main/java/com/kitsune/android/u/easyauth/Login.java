package com.kitsune.android.u.easyauth;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class Login extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        final EditText USER = findViewById(R.id.username);
        final EditText PASSWORD = findViewById(R.id.password);
        final TextView BTN_LOGIN = findViewById(R.id.btnSingIn);

        BTN_LOGIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}