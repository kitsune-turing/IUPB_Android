package com.kitsune.android.u.auth.utils;

import android.text.method.PasswordTransformationMethod;
import android.text.method.SingleLineTransformationMethod;
import android.widget.EditText;
import android.widget.ImageView;

import com.kitsune.android.u.auth.R;


/**
 * Utility class for handling password visibility toggle in EditText fields.
 */
public class PasswordUtils {


    /**
     * Toggles the visibility of the password in an EditText field and updates the toggle button icon.
     *
     * @param isPasswordVisible Current visibility state of the password.
     * @param passwordEditText The EditText containing the password.
     * @param togglePasswordButton The ImageView button used to toggle visibility.
     */
    public static void togglePasswordVisibility(boolean isPasswordVisible,
                                                EditText passwordEditText, ImageView togglePasswordButton) {
        if (isPasswordVisible) {
            // Set password to visible
            passwordEditText.setTransformationMethod(new SingleLineTransformationMethod());
            togglePasswordButton.setImageResource(R.drawable.password_close);
        } else {
            // Set password to hidden
            passwordEditText.setTransformationMethod(new PasswordTransformationMethod());
            togglePasswordButton.setImageResource(R.drawable.password_open);
        }
        // Move the cursor to the end of the text
        passwordEditText.setSelection(passwordEditText.length());
    }
}