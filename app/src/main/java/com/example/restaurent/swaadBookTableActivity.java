package com.example.restaurent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class swaadBookTableActivity extends Activity {

    private EditText name,email,mnumber,date,time;
    private Spinner nguest,place;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swaadbooktable);

        name=findViewById(R.id.se1);
        email=findViewById(R.id.se2);
        mnumber=findViewById(R.id.se3);
        date=findViewById(R.id.se4);
        time=findViewById(R.id.se5);
        nguest=findViewById(R.id.ss1);
        place=findViewById(R.id.ss2);

        button=(Button) findViewById(R.id.sbooktablesubmit);
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
                    Toast.makeText(swaadBookTableActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                int numGuests = Integer.parseInt(Nguest);
                int sum = 50 * numGuests;



                String url = "http://192.168.207.216/Run/swaadinsertData";
                String type = "register";
                BackgroundWorker backgroundWorker = new BackgroundWorker(swaadBookTableActivity.this);
                backgroundWorker.execute(url,type,Name,Email,Mnumber,Date,Time,Nguest,Place,String.valueOf(sum));


                Intent intent=new Intent(swaadBookTableActivity.this,swaadBookingConfirmationActiviy.class);
                intent.putExtra("Name",Name);
                intent.putExtra("Sum", sum);
                startActivity(intent);


            }
        });
    }

}