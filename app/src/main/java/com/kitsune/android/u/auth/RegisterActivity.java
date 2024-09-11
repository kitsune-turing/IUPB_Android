package com.kitsune.android.u.auth;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.kitsune.android.u.auth.Model.User;
import com.kitsune.android.u.auth.utils.IntentUtils;
import com.kitsune.android.u.auth.utils.PasswordUtils;
import com.kitsune.android.u.auth.utils.ValidationUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import androidx.annotation.NonNull;


public class RegisterActivity extends Activity {
    private EditText fullNameEditText, emailEditText, phoneEditText, passwordEditText, passwordConfirmEditText;
    private ImageView togglePasswordButton, toggleConfirmPasswordButton;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private PasswordUtils passwordUtils;
    private boolean isPasswordVisible = false, isConfirmPasswordVisible = false;

    private static final String TAG = "RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance("https://kitsune-app-developer-default-rtdb.firebaseio.com/");
        passwordUtils = new PasswordUtils();
        initializeUIComponents();
        setUIListeners();
    }

    private void initializeUIComponents() {
        fullNameEditText = findViewById(R.id.edit_full_name);
        emailEditText = findViewById(R.id.edit_email);
        phoneEditText = findViewById(R.id.edit_phone);
        passwordEditText = findViewById(R.id.edit_password);
        passwordConfirmEditText = findViewById(R.id.edit_confirm_password);
        togglePasswordButton = findViewById(R.id.btn_password_toggle);
        toggleConfirmPasswordButton = findViewById(R.id.btn_confirm_password_toggle);
        TextView loginButton = findViewById(R.id.btn_sign_in_prompt);

        loginButton.setOnClickListener(view -> IntentUtils.navigateTo(RegisterActivity.this, LoginActivity.class));
    }

    private void setUIListeners() {
        togglePasswordButton.setOnClickListener(v -> {
            isPasswordVisible = !isPasswordVisible;
            passwordUtils.togglePasswordVisibility(isPasswordVisible, passwordEditText, togglePasswordButton);
        });

        toggleConfirmPasswordButton.setOnClickListener(v -> {
            isConfirmPasswordVisible = !isConfirmPasswordVisible;
            passwordUtils.togglePasswordVisibility(isConfirmPasswordVisible, passwordConfirmEditText, toggleConfirmPasswordButton);
        });

        findViewById(R.id.btn_sign_up).setOnClickListener(v -> registerUser());
    }

    private void registerUser() {
        String fullName = fullNameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String phone = phoneEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String confirmPassword = passwordConfirmEditText.getText().toString().trim();

        if (!validateInputs(fullName, email, phone, password, confirmPassword)) {
            return;
        }

        createUserWithFirebase(email, password, fullName, phone);
    }

    private boolean validateInputs(String fullName, String email, String phone, String password, String confirmPassword) {
        return ValidationUtils.validateField(this, fullName, "Full Name is required") &&
                ValidationUtils.validateField(this, email, "Email is required") &&
                ValidationUtils.validateField(this, phone, "Phone number is required") &&
                ValidationUtils.validatePasswords(this, password, confirmPassword);
    }

    private void createUserWithFirebase(String email, String password, String fullName, String phone) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                storeUserDataInRealtimeDatabase(user, fullName, phone);
                                navigateToLogin();
                            }
                        } else {
                            Log.e(TAG, "Registration failed: " + task.getException().getMessage());
                            Toast.makeText(RegisterActivity.this, "Registration Failed: " + task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void storeUserDataInRealtimeDatabase(FirebaseUser user, String fullName, String phone) {
        DatabaseReference userRef = database.getReference("users").child(user.getUid());
        User newUser = new User(user.getUid(), fullName, user.getEmail(), phone);

        // Storing user data in the 'users' node with the user ID as the child node
        userRef.setValue(newUser)
                .addOnSuccessListener(aVoid -> Log.d(TAG, "User data stored successfully"))
                .addOnFailureListener(e -> Log.e(TAG, "Error storing user data", e));
    }


    private void navigateToLogin() {
        IntentUtils.navigateTo(RegisterActivity.this, LoginActivity.class);
        finish();
    }
}