package com.example.restaurent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class BookTableActivity extends Activity {

    private EditText name,email,mnumber,date,time;
    private Spinner nguest,place;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booktable);

        name=findViewById(R.id.e1);
        email=findViewById(R.id.e2);
        mnumber=findViewById(R.id.e3);
        date=findViewById(R.id.e4);
        time=findViewById(R.id.e5);
        nguest=findViewById(R.id.s1);
        place=findViewById(R.id.s2);

        button=(Button) findViewById(R.id.booktablesubmit);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String Name=name.getText().toString();
                String Email=email.getText().toString();
                String Mnumber=mnumber.getText().toString();
                String Date=date.getText().toString();
                String Time=time.getText().toString();
                String Nguest=nguest.getSelectedItem().toString();
                String Place=place.getSelectedItem().toString();

                // Validate user input
                if (Name.isEmpty() || Email.isEmpty() || Mnumber.isEmpty() || Date.isEmpty() || Time.isEmpty()) {
                    Toast.makeText(BookTableActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                int numGuests = Integer.parseInt(Nguest);
                int sum = 50 * numGuests;



                String url = "http://192.168.207.216/Run/insertData";
                String type = "register";
                BackgroundWorker backgroundWorker = new BackgroundWorker(BookTableActivity.this);
                backgroundWorker.execute(url,type,Name,Email,Mnumber,Date,Time,Nguest,Place,String.valueOf(sum));


                Intent intent=new Intent(BookTableActivity.this,BookingConfirmationActiviy.class);
                intent.putExtra("Name",Name);
                intent.putExtra("Sum", sum);
                startActivity(intent);


            }
        });
    }



}