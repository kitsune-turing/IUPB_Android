package com.kitsune.android.u.auth.Configuration;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class DataBase {
    private static final String URL = "./DataBase.txt";
    private Map<String, String> users;

    public DataBase(){
        users = new HashMap<>();
        onLoadProperties();
        loadUsers();
    }

    private void onLoadProperties() {
        File f = new File(URL);

        if (!f.exists()) {
            try {
                f.getParentFile().mkdir();
                f.createNewFile();
                try (BufferedWriter wr = new BufferedWriter(new FileWriter(f))) {
                    wr.write("");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadUsers() {
        try {
            File file = new File(URL);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;


            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    users.put(parts[0], parts[1]);
                }
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean verifyUser(String email, String password){
        return password.equals(users.get(email));
    }
}