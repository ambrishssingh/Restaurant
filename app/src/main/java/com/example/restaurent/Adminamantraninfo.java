package com.example.restaurent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Adminamantraninfo extends Activity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminamantraninfo);

        button=(Button)findViewById(R.id.adminamantrancusinfo);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Openamantrancou();
            }
        });

        button=(Button)findViewById(R.id.adminamantranordinfo);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Openamantranord();
            }
        });

    }

    public void Openamantrancou(){
        Intent intent=new Intent(Adminamantraninfo.this, Adminamantrancustomerinfo.class);
        startActivity(intent);
    }

    public void Openamantranord(){
        Intent intent=new Intent(Adminamantraninfo.this, Adminamantranorderinfo.class);
        startActivity(intent);
    }

}
