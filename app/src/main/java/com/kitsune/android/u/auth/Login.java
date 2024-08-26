package com.kitsune.android.u.auth;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.kitsune.android.u.auth.Model.User;
import com.kitsune.android.u.auth.utils.PasswordUtils;


public class Login extends Activity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private ImageView togglePasswordButton;
    private boolean isPasswordVisible = true;

    // Components
    PasswordUtils passwordUtils = new PasswordUtils();
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate called");
        setContentView(R.layout.activity_main);

        // Init components
        usernameEditText = findViewById(R.id.edit_username);
        passwordEditText = findViewById(R.id.edit_password);
        togglePasswordButton = findViewById(R.id.btn_password_toggle);
        TextView loginButton = findViewById(R.id.btn_sign_in);
        TextView registerButton = findViewById(R.id.btn_sign_up);

        // Listeners configurations
        loginButton.setOnClickListener(v -> {
            User user = new User(this);
            user.verifyCredentials(usernameEditText.getText().toString(),
                    passwordEditText.getText().toString());
        });

        registerButton.setOnClickListener(view -> startActivity(new Intent(Login.this, Register.class)));

        togglePasswordButton.setOnClickListener(view -> {
            isPasswordVisible = !isPasswordVisible;
            passwordUtils.togglePasswordVisibility(isPasswordVisible, passwordEditText, togglePasswordButton);
        });
    }
}