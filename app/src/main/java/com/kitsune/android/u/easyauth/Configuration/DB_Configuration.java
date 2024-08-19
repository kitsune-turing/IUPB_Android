package com.kitsune.android.u.easyauth.Configuration;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class DB_Configuration {
    private static final String STRING_PATH = "";
    private Map<String, String> properties;

    public DB_Configuration() {
        this.properties = new HashMap<>();

    }


    public void loadProperties(){
        File f = new File(this.STRING_PATH);

        if(!f.exists()){
            try{
                f.getParentFile().mkdir();
                f.createNewFile();

                try(BufferedWriter w = new BufferedWriter(new FileWriter(f))){
                    w.write("");
                }
            } catch (IOException e){
                 e.printStackTrace();
            }
        }
    }
}