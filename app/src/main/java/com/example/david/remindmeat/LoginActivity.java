package com.example.david.remindmeat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    private EditText loginUsername;
    private EditText loginPassword;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        Button loginButton = findViewById(R.id.btnLogin);
        Button registrationButton = findViewById(R.id.btnLinkToRegisterScreen);


        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                login();
            }
        });

        registrationButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                register();
            }
        });
    }

    private void init(){
        loginUsername = findViewById(R.id.username);
        loginPassword = findViewById(R.id.password);
        sharedPreferences = getSharedPreferences(getResources().getString(R.string.MY_PREFERENCES), MODE_PRIVATE);
    }

    private void login(){
        String username = loginUsername.getText().toString();
        String password = loginPassword.getText().toString();

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(getResources().getString(R.string.key_username), username);
        editor.putString(getResources().getString(R.string.key_password), password);
        editor.apply();

        Intent mainMenuActivity = new Intent(LoginActivity.this, MainMenuActivity.class);
        startActivity(mainMenuActivity);
    }

    private void register(){
        Intent registrationActivity = new Intent(LoginActivity.this, RegistrationActivity.class);
        startActivity(registrationActivity);
    }
}