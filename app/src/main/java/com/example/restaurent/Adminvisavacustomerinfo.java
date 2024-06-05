package com.example.restaurent;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class Adminvisavacustomerinfo extends AppCompatActivity {

    private static final String PHP_SCRIPT_URL = "http://192.168.207.216/Run/selectv";
    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminvisavacustomerinfo);

        tableLayout = findViewById(R.id.table_layout1);

        fetchDataFromPHP();
    }

    private void fetchDataFromPHP() {
        try {
            String jsonData = new FetchDataFromPHP().execute(PHP_SCRIPT_URL).get();
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("Name");
                String email = jsonObject.getString("Email_id");
                String mobileNumber = jsonObject.getString("M_number");
                String date = jsonObject.getString("Date");
                String time = jsonObject.getString("Time");
                String nGuest = jsonObject.getString("Num_geust");
                String place = jsonObject.getString("Place");
                String amount = jsonObject.getString("Amount");

                addRowToTable(name, email, mobileNumber, date, time, nGuest, place, amount);
            }
        } catch (ExecutionException | InterruptedException | JSONException e) {
            e.printStackTrace();
        }
    }

    private void addRowToTable(String name, String email, String mobileNumber, String date, String time, String nGuest, String place, String amount) {
        // Inflate the table row layout
        TableRow row = (TableRow) LayoutInflater.from(Adminvisavacustomerinfo.this).inflate(R.layout.table_row, null);

        // Find TextViews in the row
        TextView nameTextView = row.findViewById(R.id.text_name);
        TextView emailTextView = row.findViewById(R.id.text_email);
        TextView mobileNumberTextView = row.findViewById(R.id.text_mNumber);
        TextView dateTextView = row.findViewById(R.id.text_date);
        TextView timeTextView = row.findViewById(R.id.text_time);
        TextView nGuestTextView = row.findViewById(R.id.text_nGuest);
        TextView placeTextView = row.findViewById(R.id.text_place);
        TextView amountTextView = row.findViewById(R.id.text_amount);

        // Set data to TextViews
        nameTextView.setText(name);
        emailTextView.setText(email);
        mobileNumberTextView.setText(mobileNumber);
        dateTextView.setText(date);
        timeTextView.setText(time);
        nGuestTextView.setText(nGuest);
        placeTextView.setText(place);
        amountTextView.setText(amount);

        // Add the row to the table layout
        tableLayout.addView(row);
    }

    private static class FetchDataFromPHP extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder response = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                connection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return response.toString();
        }
    }

    // Method to refresh data when the refresh button is clicked
    public void refreshData(View view) {
        // Call fetchDataFromPHP to refresh data
        fetchDataFromPHP();
    }
}
