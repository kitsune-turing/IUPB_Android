package com.kitsune.android.u.auth.utils;


import android.content.Context;
import android.content.Intent;


public class IntentUtils {
    public static void navigateTo(Context context, Class<?> targetActivity) {
        Intent intent = new Intent(context, targetActivity);
        context.startActivity(intent);
    }
}