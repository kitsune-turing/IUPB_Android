package com.kitsune.android.u.auth;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.text.method.SingleLineTransformationMethod;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.kitsune.android.u.auth.Model.User;


public class Login extends Activity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private ImageView togglePasswordButton;
    private TextView loginButton;
    private TextView registerButton;
    private boolean isPasswordVisible = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init components
        usernameEditText = findViewById(R.id.edit_username);
        passwordEditText = findViewById(R.id.edit_password);
        togglePasswordButton = findViewById(R.id.btn_password_toggle);
        loginButton = findViewById(R.id.btn_sign_in);
        registerButton = findViewById(R.id.btn_sign_up);

        // Listeners configurations
        loginButton.setOnClickListener(v ->
        {
            User user = new User(this);
            user.verifyCredentials(usernameEditText.getText().toString(),
                    passwordEditText.getText().toString());
        });

        registerButton.setOnClickListener(view -> startActivity(new Intent(Login.this, Register.class)));
        togglePasswordButton.setOnClickListener(view -> togglePasswordVisibility());
    }

    private void submitLoginForm() {


    }

    private void togglePasswordVisibility() {
        if (isPasswordVisible) {
            isPasswordVisible = false;
            passwordEditText.setTransformationMethod(new PasswordTransformationMethod());
            togglePasswordButton.setImageResource(R.drawable.password_open);
        } else {
            isPasswordVisible = true;
            passwordEditText.setTransformationMethod(new SingleLineTransformationMethod());
            togglePasswordButton.setImageResource(R.drawable.password_close);
        }
        passwordEditText.setSelection(passwordEditText.length());
    }
}