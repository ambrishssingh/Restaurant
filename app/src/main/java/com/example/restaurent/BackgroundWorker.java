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

public class BackgroundWorker extends AsyncTask<String, Void, String> {
    private Context context;

    BackgroundWorker(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String loginUrl = params[0];
        String type = params[1];
        String name = params[2];
        String email = params[3];
        String mNumber = params[4];
        String date = params[5];
        String time = params[6];
        String nGuest = params[7];
        String place = params[8];
        String Sum=params[9];

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
                        .append(URLEncoder.encode("email_id", "UTF-8")).append("=").append(URLEncoder.encode(email, "UTF-8")).append("&")
                        .append(URLEncoder.encode("m_number", "UTF-8")).append("=").append(URLEncoder.encode(mNumber, "UTF-8")).append("&")
                        .append(URLEncoder.encode("date", "UTF-8")).append("=").append(URLEncoder.encode(date, "UTF-8")).append("&")
                        .append(URLEncoder.encode("time", "UTF-8")).append("=").append(URLEncoder.encode(time, "UTF-8")).append("&")
                        .append(URLEncoder.encode("Guest", "UTF-8")).append("=").append(URLEncoder.encode(nGuest, "UTF-8")).append("&")
                        .append(URLEncoder.encode("place", "UTF-8")).append("=").append(URLEncoder.encode(place, "UTF-8")).append("&")
                        .append(URLEncoder.encode("Amount", "UTF-8")).append("=").append(URLEncoder.encode(Sum, "UTF-8"));
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
