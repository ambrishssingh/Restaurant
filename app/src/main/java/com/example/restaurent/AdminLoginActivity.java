package com.example.restaurent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLoginActivity extends Activity {

    EditText usernameLogin;
    EditText passwordLogin;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminlogin);

        usernameLogin = findViewById(R.id.userid);
        passwordLogin = findViewById(R.id.passward);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameLogin.getText().toString();
                String password = passwordLogin.getText().toString();
                String url = "http://192.168.207.216/Run/validateData";
                String type = "login";
                BackGroundLogin backGroundLogin = new BackGroundLogin(AdminLoginActivity.this);
                backGroundLogin.execute(url, type, username, password);
            }
        });
    }

    public void loginSuccess() {
        Intent intent = new Intent(AdminLoginActivity.this, Adminhotelpage.class);
        startActivity(intent);
    }

    public void loginFailed(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}