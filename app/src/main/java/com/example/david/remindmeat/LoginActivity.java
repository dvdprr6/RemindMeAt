package com.example.david.remindmeat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    EditText loginUsername;
    EditText loginPassword;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginUsername = findViewById(R.id.username);
        loginPassword = findViewById(R.id.password);

        Button loginButton = findViewById(R.id.btnLogin);
        Button registerationButton = findViewById(R.id.btnLinkToRegisterScreen);

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String username = loginUsername.getText().toString();
                String password = loginPassword.getText().toString();

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString(getResources().getString(R.string.key_username), username);
                editor.putString(getResources().getString(R.string.key_password), password);
                editor.apply();

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