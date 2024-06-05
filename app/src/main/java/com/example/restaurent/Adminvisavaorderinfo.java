
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

public class Adminvisavaorderinfo extends AppCompatActivity {

    private static final String PHP_SCRIPT_URL = "http://192.168.207.216/Run/selecvo"; // Change the URL accordingly
    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminvisavaorderinfo);

        tableLayout = findViewById(R.id.table_layout2);

        fetchDataFromPHP();
    }

    private void fetchDataFromPHP() {
        try {
            String jsonData = new FetchDataFromPHP().execute(PHP_SCRIPT_URL).get();
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String cName = jsonObject.getString("CName");
                String item = jsonObject.getString("Item");
                String value = jsonObject.getString("Value");

                addRowToTable( cName, item, value);
            }
        } catch (ExecutionException | InterruptedException | JSONException e) {
            e.printStackTrace();
        }
    }

    private void addRowToTable(String cName, String item, String value) {
        // Inflate the table row layout
        TableRow row = (TableRow) LayoutInflater.from(Adminvisavaorderinfo.this).inflate(R.layout.order_row, null);

        // Find TextViews in the row
        TextView cNameTextView = row.findViewById(R.id.text_name);
        TextView itemTextView = row.findViewById(R.id.text_item);
        TextView valueTextView = row.findViewById(R.id.text_amount);

        // Set data to TextViews
        cNameTextView.setText(cName);
        itemTextView.setText(item);
        valueTextView.setText(value);

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
