package com.example.restaurent;


import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

// Import statements...

public class BackGroundMenu extends AsyncTask<String, Void, String> {
    private Context context;

    BackGroundMenu(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String loginUrl = params[0];
        String type = params[1];
        String name = params[2];
        String data = params[3];
        String value = params[4];

        try {
            URL url = new URL(loginUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

            StringBuilder postData = new StringBuilder();
            if (type.equals("register")) {
                postData.append(URLEncoder.encode("name", "UTF-8")).append("=").append(URLEncoder.encode(name, "UTF-8")).append("&")
                        .append(URLEncoder.encode("OrderDetail", "UTF-8")).append("=").append(URLEncoder.encode(data, "UTF-8")).append("&")
                        .append(URLEncoder.encode("Value", "UTF-8")).append("=").append(URLEncoder.encode(value, "UTF-8")).append("&");
            }

            bufferedWriter.write(postData.toString());
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

            StringBuilder result = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }

            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return result.toString();

        } catch (IOException e) {
            return "Exception: " + e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (result.startsWith("Exception")) {
            // If an exception occurred during execution
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("An error occurred: " + result)
                    .setTitle("Error")
                    .setPositiveButton("OK", null);
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            // If no exception occurred, display the result (which could also be an error message)
            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        }
    }

}
