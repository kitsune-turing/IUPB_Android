package com.kitsune.android.u.auth.utils;


import android.text.method.PasswordTransformationMethod;
import android.text.method.SingleLineTransformationMethod;
import android.widget.EditText;
import android.widget.ImageView;

import com.kitsune.android.u.auth.R;


public class PasswordUtils {

    /**
     * Change visibility for password in the component EditText and update icon of button.
     *
     * @param isPasswordVisible This is visibility for password in the moment.
     * @param passwordEditText EditText contain password.
     * @param togglePasswordButton ImageView is a manager change state the format for the EditText in the UI.
     * @return Is a function void, not return value
     */
    public void togglePasswordVisibility(boolean isPasswordVisible,
                                          EditText passwordEditText, ImageView togglePasswordButton) {
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