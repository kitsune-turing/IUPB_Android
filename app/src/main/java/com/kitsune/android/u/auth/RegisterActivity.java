package com.kitsune.android.u.auth;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.kitsune.android.u.auth.utils.PasswordUtils;
import com.kitsune.android.u.auth.utils.ValidationUtils;


public class Register extends Activity {
    private ImageView togglePasswordButton;
    private ImageView toggleConfirmPasswordButton;
    private EditText passwordEditText;
    private EditText passwordConfirmEditText;
    private EditText fullNameEditText;
    private EditText emailEditText;
    private EditText phoneEditText;
    private boolean isPasswordVisible = true;
    private boolean isConfirmPasswordVisible = true;

    PasswordUtils passwordUtils = new PasswordUtils();
    private static final String TAG = "RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate called");
        setContentView(R.layout.activity_register);

        // Initialize components
        fullNameEditText = findViewById(R.id.edit_full_name);
        emailEditText = findViewById(R.id.edit_email);
        phoneEditText = findViewById(R.id.edit_phone);
        passwordEditText = findViewById(R.id.edit_password);
        passwordConfirmEditText = findViewById(R.id.edit_confirm_password);
        togglePasswordButton = findViewById(R.id.btn_password_toggle);
        toggleConfirmPasswordButton = findViewById(R.id.btn_confirm_password_toggle);
        TextView loginButton = findViewById(R.id.btn_sign_in_prompt);

        // Set Listeners
        loginButton.setOnClickListener(view -> startActivity(new Intent(Register.this, Login.class)));

        togglePasswordButton.setOnClickListener(v -> {
            isPasswordVisible = !isPasswordVisible;
            passwordUtils.togglePasswordVisibility(isPasswordVisible, passwordEditText, togglePasswordButton);
        });

        toggleConfirmPasswordButton.setOnClickListener(v -> {
            isConfirmPasswordVisible = !isConfirmPasswordVisible;
            passwordUtils.togglePasswordVisibility(isConfirmPasswordVisible, passwordConfirmEditText, toggleConfirmPasswordButton);
        });

        findViewById(R.id.btn_sign_up).setOnClickListener(v -> {
            String fullName = fullNameEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String phone = phoneEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            String confirmPassword = passwordConfirmEditText.getText().toString().trim();

            if (!ValidationUtils.validateField(this, fullName, "Full Name is required") ||
                    !ValidationUtils.validateField(this, email, "Email is required") ||
                    !ValidationUtils.validateField(this, phone, "Phone number is required") ||
                    !ValidationUtils.validatePasswords(this, password, confirmPassword)) {
                return;
            }

            showConfirmationModal();
        });
    }

    @SuppressWarnings("SetTextI18n")
    private void showConfirmationModal() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_confirmation);
        dialog.setCancelable(false);

        TextView confirmationMessage = dialog.findViewById(R.id.text_confirmation_message);
        confirmationMessage.setText("Full Name: " + fullNameEditText.getText().toString().trim() + "\n" +
                "Email: " + emailEditText.getText().toString().trim() + "\n" +
                "Phone: " + phoneEditText.getText().toString().trim() + "\n" +
                "Password: " + passwordEditText.getText().toString().trim() + "\n" +
                "Confirm Password: " + passwordConfirmEditText.getText().toString().trim());

        Button acceptButton = dialog.findViewById(R.id.btn_accept);
        acceptButton.setOnClickListener(v -> {
            dialog.dismiss();
            startActivity(new Intent(Register.this, Login.class));
        });

        dialog.show();
    }
}