package com.example.restaurent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BookMenuActiviy extends Activity {
    private Button button;
    private int c1=0,c2=0,c3=0,c4=0,c5=0,c6=0,c7=0;
    private String name;
    private int sum12;
    private int sum=0;
    private StringBuilder orderBuilder = new StringBuilder();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menubooking);

        Intent intent = getIntent();
        name = intent.getStringExtra("Name");
        sum12 = intent.getIntExtra("Sum", 0); // Getting integer extra

        Button paneer = findViewById(R.id.Panner);
        Button pc = findViewById(R.id.PC);
        Button bhindi = findViewById(R.id.Bhindi);
        Button pt = findViewById(R.id.PT);
        Button vots = findViewById(R.id.VOTS);
        Button pcc = findViewById(R.id.PCC);
        Button dfjr = findViewById(R.id.DFJR);

        paneer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sum += 110; // Adjusted to integer addition
                c1++;
                TextView P = findViewById(R.id.Panner0);
                P.setText("count = "+String.valueOf(c1));
                addOrder("Panner Chilli");
                Toast.makeText(BookMenuActiviy.this, "Amount is" + sum, Toast.LENGTH_LONG).show();
            }
        });

        pt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sum += 80; // Adjusted to integer addition
                c2++;
                TextView P1 = findViewById(R.id.PT0);
                P1.setText("count = "+String.valueOf(c2));
                addOrder("Fries Special Potato Twister");
                Toast.makeText(BookMenuActiviy.this, "Amount is" + sum, Toast.LENGTH_LONG).show();
            }
        });

        pc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sum += 80; // Adjusted to integer addition
                c3++;
                TextView P2 = findViewById(R.id.PC0);
                P2.setText("count = "+String.valueOf(c3));
                addOrder("Panner Chowmin");
                Toast.makeText(BookMenuActiviy.this, "Amount is" + sum, Toast.LENGTH_LONG).show();
            }
        });

        bhindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sum += 100; // Adjusted to integer addition
                c4++;
                TextView P3 = findViewById(R.id.Bhindi0);
                P3.setText("count = "+String.valueOf(c4));
                addOrder("Bhindi Masala");
                Toast.makeText(BookMenuActiviy.this, "Amount is" + sum, Toast.LENGTH_LONG).show();
            }
        });

        vots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sum += 60; // Adjusted to integer addition
                c5++;
                TextView P4 = findViewById(R.id.VOTS0);
                P4.setText("count = "+String.valueOf(c5));
                addOrder("Veg Onion Tomato Sandwich");
                Toast.makeText(BookMenuActiviy.this, "Amount is" + sum, Toast.LENGTH_LONG).show();
            }
        });

        pcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sum += 100; // Adjusted to integer addition
                c6++;
                TextView P5 = findViewById(R.id.PCC0);
                P5.setText("count = "+String.valueOf(c6));
                addOrder("Pindi Chole with Chapati Lunchbox");
                Toast.makeText(BookMenuActiviy.this, "Amount is" + sum, Toast.LENGTH_LONG).show();
            }
        });

        dfjr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sum += 100; // Adjusted to integer addition
                c7++;
                TextView P6 = findViewById(R.id.DFJR0);
                P6.setText("count = "+String.valueOf(c7));
                addOrder("Dal Fry With Jeera Rice");
                Toast.makeText(BookMenuActiviy.this, "Amount is" + sum, Toast.LENGTH_LONG).show();
            }
        });

        button = findViewById(R.id.Bookmenucon);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenPayment();
            }
        });
    }

    private void addOrder(String item) {
        if (orderBuilder.length() > 0)
            orderBuilder.append(", ");
        orderBuilder.append(item);
    }

    public void OpenPayment() {
        String url = "http://192.168.207.216/Run/insertDataM";
        String type = "register";
        String orderDetails = orderBuilder.toString();
        BackGroundMenu backGroundMenu = new BackGroundMenu(BookMenuActiviy.this);
        backGroundMenu.execute(url, type, name, orderDetails, String.valueOf(sum));
        int halfsum =sum12+ sum / 2;

        Intent intent = new Intent(BookMenuActiviy.this, PaymentActivity.class);
        intent.putExtra("totalAmount", halfsum);
        intent.putExtra("Name",name);
        Toast.makeText(BookMenuActiviy.this, "Total Amount is=" + halfsum, Toast.LENGTH_LONG).show();
        startActivity(intent);
    }
}