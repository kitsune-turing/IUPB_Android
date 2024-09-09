package com.kitsune.android.u.auth;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kitsune.android.u.auth.utils.PasswordUtils;


public class Login extends Activity {
    private EditText usernameEditText, passwordEditText;
    private ImageView togglePasswordButton;
    private boolean isPasswordVisible = false;
    private FirebaseAuth mAuth;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate called");
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Initialize components
        usernameEditText = findViewById(R.id.edit_username);
        passwordEditText = findViewById(R.id.edit_password);
        togglePasswordButton = findViewById(R.id.btn_password_toggle);
        TextView loginButton = findViewById(R.id.btn_sign_in);
        TextView registerButton = findViewById(R.id.btn_sign_up);

        // Set up listeners
        loginButton.setOnClickListener(v -> signInUser());
        registerButton.setOnClickListener(view -> startActivity(new Intent(Login.this, Register.class)));

        togglePasswordButton.setOnClickListener(view -> {
            isPasswordVisible = !isPasswordVisible;
            PasswordUtils.togglePasswordVisibility(isPasswordVisible, passwordEditText, togglePasswordButton);
        });
    }

    private void signInUser() {
        String email = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            showToast("Email and Password are required");
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success
                        FirebaseUser user = mAuth.getCurrentUser();
                        Log.d(TAG, "signInWithEmail:success");
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // Sign in failed
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                        showToast("Authentication failed.");
                    }
                });
    }

    private void showToast(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}