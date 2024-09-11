package com.kitsune.android.u.auth;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.kitsune.android.u.auth.utils.PasswordUtils;


/**
 * LoginActivity handles the user login process.
 * It authenticates the user via Firebase and provides
 * UI interactions such as toggling password visibility and navigating to registration.
 */
public class LoginActivity extends Activity {
    private static final String TAG = "LoginActivity";
    private static final String ERROR_EMPTY_FIELDS = "Email and Password are required";
    private static final String ERROR_AUTH_FAILED = "Authentication failed.";

    private EditText usernameEditText, passwordEditText;
    private ImageView togglePasswordButton;
    private boolean isPasswordVisible = false;
    private FirebaseAuth mAuth;

    /**
     * Called when the activity is first created.
     * Initializes UI components and sets up event listeners.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down,
     * this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate called");
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Initialize UI components
        usernameEditText = findViewById(R.id.edit_username);
        passwordEditText = findViewById(R.id.edit_password);
        togglePasswordButton = findViewById(R.id.btn_password_toggle);
        TextView loginButton = findViewById(R.id.btn_sign_in);
        TextView registerButton = findViewById(R.id.btn_sign_up);

        // Set up listeners for login and registration buttons
        loginButton.setOnClickListener(v -> signInUser());
        registerButton.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));

        // Set up listener for toggling password visibility
        togglePasswordButton.setOnClickListener(view -> {
            isPasswordVisible = !isPasswordVisible;
            PasswordUtils.togglePasswordVisibility(isPasswordVisible, passwordEditText, togglePasswordButton);
        });
    }

    /**
     * Attempts to sign in the user using the Firebase authentication service.
     * Validates email and password fields and, if successful, redirects the user to the HomeActivity.
     */
    private void signInUser() {
        String email = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            showToast(ERROR_EMPTY_FIELDS);
            return;
        }

        // Authenticate user using Firebase Auth
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "signInWithEmail:success");
                        navigateToHome();
                    } else {
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                        showToast(ERROR_AUTH_FAILED);
                    }
                });
    }

    /**
     * Navigates the user to the HomeActivity after a successful login.
     */
    private void navigateToHome() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Displays a Toast message to the user.
     *
     * @param message The message to display in the Toast.
     */
    private void showToast(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}