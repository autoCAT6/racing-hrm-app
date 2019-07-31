package com.clj.blesample.operation;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


    public class PostHRM extends AsyncTask<String, Void, String> {

        public static final String POST_URL = "https://b0ienqfdf7.execute-api.cn-north-1.amazonaws.com.cn/beta/data";

        @Override
        protected String doInBackground(String... params) {

            try {
                URL url = new URL(POST_URL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Accept", "*/*");

                JSONObject jsonObject = new JSONObject();
                jsonObject.put(params[0], params[1]);


                DataOutputStream os = new DataOutputStream(connection.getOutputStream());
                os.writeBytes(jsonObject.toString());
                os.flush();
                os.close();

                Log.i("STATUS", String.valueOf(connection.getResponseCode()));
                Log.i("MSG" , connection.getResponseMessage());

                connection.disconnect();

            } catch (Exception e) {
                Log.e(e.toString(), "Something with request");
            }

            return null;
        }
    }

