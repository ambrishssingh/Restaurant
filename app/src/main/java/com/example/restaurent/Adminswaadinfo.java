package com.example.restaurent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Adminswaadinfo extends Activity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminswaadinfo);

        button=(Button)findViewById(R.id.adminswaadcusinfo);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Openamantrancou();
            }
        });

        button=(Button)findViewById(R.id.adminswaadordinfo);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Openamantranord();
            }
        });

    }

    public void Openamantrancou(){
        Intent intent=new Intent(Adminswaadinfo.this, Adminswaadcustomerinfo.class);
        startActivity(intent);
    }

    public void Openamantranord(){
        Intent intent=new Intent(Adminswaadinfo.this, Adminswaadorderinfo.class);
        startActivity(intent);
    }
}
