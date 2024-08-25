package com.kitsune.android.u.auth.Configuration;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class DataBase {
    private static final String FILE_NAME = "DataBase.txt";
    private Map<String, String> users;
    private Context context;

    public DataBase(Context context) {
        this.context = context;
        users = new HashMap<>();
        loadUsers();
    }

    private void loadUsers() {
        File file = new File(context.getFilesDir(), FILE_NAME);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(":", 2);
                    if (parts.length == 2) {
                        users.put(parts[0].trim(), parts[1].trim());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File not found: " + file.getAbsolutePath());
        }
    }

    public void verifyUser(String email, String password) {
        boolean is = users.containsKey(email);
        System.out.println(is + " - " );
        boolean isValidUser = password.equals(users.get(email));

        if (isValidUser)
            showUserCorrectModal("The user credentials are correct.");
        else
            showUserCorrectModal("The user credentials are incorrect.");
    }

    private void showUserCorrectModal(String message) {
        ((Activity) context).runOnUiThread(() -> {
            new AlertDialog.Builder(context)
                    .setTitle("User Verification")
                    .setMessage(message)
                    .setPositiveButton(android.R.string.ok, null)
                    .show();
        });
    }
}