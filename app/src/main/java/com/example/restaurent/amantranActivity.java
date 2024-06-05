package com.example.restaurent;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;
import com.example.restaurent.BookTableActivity;
import com.example.restaurent.ExploreMenuActivity;
import com.example.restaurent.R;



public class amantranActivity extends AppCompatActivity {

    private Button button,button2,button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.amantran);

        // Setup Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        button = (Button) findViewById(R.id.booktable);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                OpenVisavaTable();
            }
        });

        button2 = (Button) findViewById(R.id.menu);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                OpenVisavaMenu();
            }
        });

        button3 = (Button) findViewById(R.id.contact);
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                OpenVisavacontact();
            }
        });
    }

    // Inflate menu resource
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }

    // Handle menu item clicks
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.booktab) {
            // Open the new activity for this menu item
            OpenVisavaTable();
            return true;
        } else if (id == R.id.menutab) {
            OpenVisavaMenu();
            return true;
        } else if (id == R.id.hometab) {
            OpenVisavaHome();
            return true;
        }else if (id == R.id.contacttab) {
            OpenVisavacontact();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void OpenVisavaTable(){
        Intent intent=new Intent(amantranActivity.this,amantranBookTableActivity.class);
        startActivity(intent);
    }

    public void OpenVisavaMenu(){
        Intent intent=new Intent(amantranActivity.this,ExploreMenuActivity.class);
        startActivity(intent);
    }

    public void OpenVisavaHome(){
        Intent intent=new Intent(amantranActivity.this, amantranActivity.class);
        startActivity(intent);
    }

    public void OpenVisavacontact(){
        Intent intent=new Intent(amantranActivity.this,ContantActivity.class);
        startActivity(intent);
    }
}
