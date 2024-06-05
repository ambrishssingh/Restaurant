package com.example.restaurent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class swaadBookingConfirmationActiviy extends Activity {

    private Button button, button2, button3;
    private String name;
    private int sum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swaadbookingconfirmation);

        Intent intent = getIntent();
        name = intent.getStringExtra("Name");
        sum = intent.getExtras().getInt("Sum");

        button = findViewById(R.id.sBookmenu);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBookMenuActivity();
            }
        });



        button3 = findViewById(R.id.spayment);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPaymentActivity();
            }
        });
    }

    public void openBookMenuActivity() {
        Intent intent = new Intent(swaadBookingConfirmationActiviy.this, swaadBookMenuActiviy.class);
        intent.putExtra("Name", name);
        intent.putExtra("Sum", sum);
        startActivity(intent);
    }



    public void openPaymentActivity() {
        Intent intent = new Intent(swaadBookingConfirmationActiviy.this, swaadPaymentActivity.class);
        intent.putExtra("totalAmount", sum);
        startActivity(intent);
    }
}
