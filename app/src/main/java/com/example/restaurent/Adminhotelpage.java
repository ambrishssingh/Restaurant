package com.example.restaurent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Adminhotelpage extends Activity {

    private Button button1,button2,button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminhotelpage);

        button1 = (Button) findViewById(R.id.adminvisava);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenVisava();
            }
        });

        button2 = (Button) findViewById(R.id.adminswaad);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenSwaad();
            }
        });

        button3 = (Button) findViewById(R.id.adminamantran);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Openamantran();
            }
        });

    }

    public void OpenVisava() {
        Intent intent = new Intent(Adminhotelpage.this, AdminVisavainfo.class);
        startActivity(intent);
    }

    public void OpenSwaad() {
        Intent intent = new Intent(Adminhotelpage.this, Adminswaadinfo.class);
        startActivity(intent);
    }

    public void Openamantran() {
        Intent intent = new Intent(Adminhotelpage.this, Adminamantraninfo.class);
        startActivity(intent);
    }
}
