package com.example.restaurent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class amantranPaymentActivity extends Activity {

    private Button button,button2,button3;

    String sum1,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.amantranpayment);

        Intent intent = getIntent();
        int sum = intent.getIntExtra("totalAmount", 0); // Retrieving the sum value
        sum1 = String.valueOf(sum);
        name=intent.getExtras().getString("Name");

        button=(Button) findViewById(R.id.apaytm);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Openpay();
            }
        });

        button2=(Button) findViewById(R.id.aphonepe);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Openpay();
            }
        });

        button3=(Button) findViewById(R.id.agpay);
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Openpay();
            }
        });
    }

    public void Openpay(){
        Intent intent = new Intent(amantranPaymentActivity.this, amantranFinalPaymentActivity.class);
        intent.putExtra("totalAmount", sum1);
        intent.putExtra("Name",name);
        Log.d("TotalAmount", "Value: " + sum1); // Log the value
        startActivity(intent);
    }

}