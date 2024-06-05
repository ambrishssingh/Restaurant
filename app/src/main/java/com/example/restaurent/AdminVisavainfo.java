package com.example.restaurent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminVisavainfo extends Activity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminvisavainnfo);

        button=(Button)findViewById(R.id.adminvisavacusinfo);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Openamantrancou();
            }
        });

        button=(Button)findViewById(R.id.adminvisavaordinfo);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Openamantranord();
            }
        });

    }

    public void Openamantrancou(){
        Intent intent=new Intent(AdminVisavainfo.this, Adminvisavacustomerinfo.class);
        startActivity(intent);
    }

    public void Openamantranord(){
        Intent intent=new Intent(AdminVisavainfo.this, Adminvisavaorderinfo.class);
        startActivity(intent);
    }
}
