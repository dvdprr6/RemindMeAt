package com.example.david.remindmeat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = findViewById(R.id.btnLogin);
        Button registerationButton = findViewById(R.id.btnLinkToRegisterScreen);

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent remindMeListActivity = new Intent(LoginActivity.this, RemindMeAtListActivity.class);
                startActivity(remindMeListActivity);
            }
        });

        registerationButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent registrationActivity = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(registrationActivity);
            }
        });
    }
}