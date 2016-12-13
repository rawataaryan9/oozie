package com.dp.batch.cp.config;

import org.json.JSONObject;

import java.io.*;
import java.util.Map;

/**
 * Created by aman on 20/10/16.
 */
public class OozieConfig {

    private String inputJsonFilePath;

    public OozieConfig(String inputJsonFilePath) {
        this.inputJsonFilePath = inputJsonFilePath;
    }

    public JSONObject getParamsMap(){
        String jsonData = "";
        BufferedReader bf = null;

        try {
            String line = null;
            bf = new BufferedReader(new FileReader(inputJsonFilePath));
            while((line=bf.readLine())!=null){
                jsonData += line + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Json Data is "+jsonData);
        return new JSONObject(jsonData);
    }
}
