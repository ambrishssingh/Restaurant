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
import java.net.URL;
import java.net.URLEncoder;

public class DeleteActivity extends AsyncTask<String, Void, String> {
    private Context context;

    DeleteActivity(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String loginUrl = params[0];
        String type = params[1];
        String name = params[2];

        try {
            URL url = new URL(loginUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

            StringBuilder postData = new StringBuilder();
            if (type.equals("Delete")) {
                postData.append(URLEncoder.encode("name", "UTF-8")).append("=").append(URLEncoder.encode(name, "UTF-8"));
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
        if (result.equals("Data deletion successful")) {
            // If deletion is successful
            Toast.makeText(context, "Data deleted successfully", Toast.LENGTH_LONG).show();
        } else if (result.startsWith("Exception")) {
            // If an exception occurred during execution
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("An error occurred: " + result)
                    .setTitle("Error")
                    .setPositiveButton("OK", null);
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            // If deletion failed or any other unexpected response
            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        }
    }
}
