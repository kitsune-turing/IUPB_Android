package com.kitsune.android.u.auth.utils;


import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;


/**
 * Utility class for validating input fields and passwords in user forms.
 */
public class ValidationUtils {
    private static final String ERROR_PASSWORD_REQUIRED = "Password is required";
    private static final String ERROR_CONFIRM_PASSWORD_REQUIRED = "Confirm Password is required";
    private static final String ERROR_PASSWORDS_DO_NOT_MATCH = "Passwords do not match";

    /**
     * Validates if a given field is empty.
     *
     * @param context The context where the validation occurs.
     * @param value The value to validate.
     * @param errorMessage The error message to display if validation fails.
     * @return True if the field is not empty, otherwise false.
     */
    public static boolean validateField(Context context, String value, String errorMessage) {
        if (TextUtils.isEmpty(value)) {
            showToast(context, errorMessage);
            return false;
        }
        return true;
    }

    /**
     * Validates the password and confirm password fields.
     * Ensures both fields are not empty and that they match.
     *
     * @param context The context where the validation occurs.
     * @param password The password entered by the user.
     * @param confirmPassword The confirmation password entered by the user.
     * @return True if both passwords are valid and match, otherwise false.
     */
    public static boolean validatePasswords(Context context, String password, String confirmPassword) {
        if (TextUtils.isEmpty(password)) {
            showToast(context, ERROR_PASSWORD_REQUIRED);
            return false;
        }
        if (TextUtils.isEmpty(confirmPassword)) {
            showToast(context, ERROR_CONFIRM_PASSWORD_REQUIRED);
            return false;
        }
        if (!password.equals(confirmPassword)) {
            showToast(context, ERROR_PASSWORDS_DO_NOT_MATCH);
            return false;
        }
        return true;
    }

    /**
     * Displays a Toast message.
     *
     * @param context The context where the Toast is displayed.
     * @param message The message to display in the Toast.
     */
    private static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static boolean validatePasswordStrength(Context context, String password) {
        if (password.length() < 8 || !password.matches(".*[A-Z].*") || !password.matches(".*[0-9].*")) {
            showToast(context, "Password must be at least 8 characters long, contain an uppercase letter, and a number.");
            return false;
        }
        return true;
    }
}