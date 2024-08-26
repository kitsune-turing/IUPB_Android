package com.kitsune.android.u.auth.utils;


import android.content.Context;
import android.widget.Toast;


public class ValidationUtils {

    public static boolean validateField(Context context, String value, String errorMessage) {
        if (value.isEmpty()) {
            showToast(context, errorMessage);
            return false;
        }
        return true;
    }

    public static boolean validatePasswords(Context context, String password, String confirmPassword) {
        if (password.isEmpty()) {
            showToast(context, "Password is required");
            return false;
        }
        if (confirmPassword.isEmpty()) {
            showToast(context, "Confirm Password is required");
            return false;
        }
        if (!password.equals(confirmPassword)) {
            showToast(context, "Passwords do not match");
            return false;
        }
        return true;
    }

    private static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}