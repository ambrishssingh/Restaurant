package com.example.restaurent;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FinalPaymentActivity extends Activity {

    private EditText payment;
    private Button btn1,btn2;
    String name;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paytm);

        Intent intent = getIntent();
        String sum=intent.getExtras().getString("totalAmount");
        name=intent.getExtras().getString("Name");
        btn1=findViewById(R.id.cancelpay);
        btn2=findViewById(R.id.finalpay);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FinalPaymentActivity.this,MainActivity.class);

                // Show Toast message
                Toast.makeText(FinalPaymentActivity.this,"Payment successfully Done",Toast.LENGTH_LONG).show();

                // Create a Handler to delay finishing the activity
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Start MainActivity after 2 seconds
                        startActivity(intent);
                    }
                }, 2000);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name=name.toString();
                String url = "http://192.168.207.216/Run/Delete";
                String type = "Delete";
                DeleteActivity deleteActivity = new DeleteActivity(FinalPaymentActivity.this);
                deleteActivity.execute(url,type,Name);
                Intent intent=new Intent(FinalPaymentActivity.this,MainActivity.class);

                // Show Toast message
                Toast.makeText(FinalPaymentActivity.this,"Payment canceled successfully",Toast.LENGTH_LONG).show();

                // Create a Handler to delay finishing the activity
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Start MainActivity after 2 seconds
                        startActivity(intent);
                    }
                }, 2000); // 2000 milliseconds = 2 seconds
            }
        });



        TextView P5 = findViewById(R.id.paymentf);
        P5.setText(" "+sum);
    }
}