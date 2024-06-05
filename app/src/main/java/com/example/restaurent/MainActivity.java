package com.example.restaurent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button button   ,button2,button3,btnconnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=(Button) findViewById(R.id.visava);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                OpenVisava();
            }
        });

        button=(Button) findViewById(R.id.swaad);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                OpenSwaad();
            }
        });

        button=(Button) findViewById(R.id.amantran);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Openamantran();
            }
        });

        button2=(Button) findViewById(R.id.admin);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){ Openlogin();}
        });
    }

    public void OpenVisava(){
        Intent intent=new Intent(MainActivity.this,VisavaActivity.class);
        startActivity(intent);
    }

    public void OpenSwaad(){
        Intent intent=new Intent(MainActivity.this,SwaadActivity.class);
        startActivity(intent);
    }

    public void Openamantran(){
        Intent intent=new Intent(MainActivity.this,amantranActivity.class);
        startActivity(intent);
    }

    public void Openlogin(){
        Intent intent=new Intent(MainActivity.this, AdminLoginActivity.class);
        startActivity(intent);
    }

}