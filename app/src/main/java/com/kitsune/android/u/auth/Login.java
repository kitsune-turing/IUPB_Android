package com.kitsune.android.u.auth;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class Login extends Activity {

    private boolean password_show = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText USER = findViewById(R.id.username);
        final EditText PASS = findViewById(R.id.password);
        final ImageView BTN_PASS = findViewById(R.id.btnPassIcon);
        final TextView BTN_LOGIN = findViewById(R.id.btnSingIn);
        final TextView BTN_REGSITER = findViewById(R.id.btnSingUp);

        BTN_LOGIN.setOnClickListener(v -> dataForm());
        BTN_REGSITER.setOnClickListener(view -> startActivity(new Intent(Login.this, Register.class)));

        BTN_PASS.setOnClickListener(view -> {
            if(password_show) {
                password_show = false;
                PASS.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                BTN_PASS.setImageResource(R.drawable.password_open);
            }else {
                password_show = true;
                PASS.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                BTN_PASS.setImageResource(R.drawable.password_close);
            }

            PASS.setSelection(PASS.length());
        });
    }

    private void dataForm(){

    }
}