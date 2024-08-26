package com.kitsune.android.u.auth;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class ConfirmationActivity extends Activity {
    private static final String TAG = "ConfirmationActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate called");
        setContentView(R.layout.activity_confirmation);

        // Get data from Intent
        String fullName = getIntent().getStringExtra("fullName");
        String email = getIntent().getStringExtra("email");
        String phone = getIntent().getStringExtra("phone");
        String password = getIntent().getStringExtra("password");
        String confirmPassword = getIntent().getStringExtra("confirmPassword");

        // Log the received data for debugging
        Log.d(TAG, "Full Name: " + fullName);
        Log.d(TAG, "Email: " + email);
        Log.d(TAG, "Phone: " + phone);
        Log.d(TAG, "Password: " + password);
        Log.d(TAG, "Confirm Password: " + confirmPassword);

        // Display data in TextView
        TextView confirmationTextView = findViewById(R.id.text_confirmation_message);
        String confirmationText = "Full Name: " + fullName + "\n" +
                "Email: " + email + "\n" +
                "Phone: " + phone + "\n" +
                "Password: " + password + "\n" +
                "Confirm Password: " + confirmPassword;
        confirmationTextView.setText(confirmationText);
    }
}