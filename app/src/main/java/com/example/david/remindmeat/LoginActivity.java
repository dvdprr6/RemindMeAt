package com.example.david.remindmeat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.david.remindmeat.callback.UserCallback;
import com.example.david.remindmeat.implementation.UserImplementation;
import com.example.david.remindmeat.model.User;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements UserCallback{
    private EditText loginEmail;
    private EditText loginPassword;
    private String email;
    private String password;
    private SharedPreferences sharedPreferences;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }

    public void loginButton(View view){
        login();
    }

    public void registerButton(View view){
        register();
    }

    private void init(){
        loginEmail = findViewById(R.id.email);
        loginPassword = findViewById(R.id.password);
        //sharedPreferences = getSharedPreferences(getResources().getString(R.string.MY_PREFERENCES), MODE_PRIVATE);
    }

    private void login(){
        email = loginEmail.getText().toString().toLowerCase();
        password = loginPassword.getText().toString();

        //SharedPreferences.Editor editor = sharedPreferences.edit();

        //editor.putString(getResources().getString(R.string.key_email), email);
        //editor.putString(getResources().getString(R.string.key_password), password);
        //editor.apply();

        //if(email != "" && password != null) {
        if(!email.equals("") && !password.equals("")){

            UserImplementation userImplementation = new UserImplementation(this);

            userImplementation.findUserByEmail(this, email);
        }else{
            Toast.makeText(this, "Login Failed",Toast.LENGTH_LONG).show();
        }


    }

    private void register(){
        Intent registrationActivity = new Intent(LoginActivity.this, RegistrationActivity.class);
        startActivity(registrationActivity);
    }

    @Override
    public void onUserLoaded(List<User> users) {
        for(User user : users){
            Log.i(TAG, user.getFirstName());
            Log.i(TAG, user.getLastName());
            Log.i(TAG, user.getEmail());
            Log.i(TAG, user.getPassword());
        }
    }

    @Override
    public void onUserLoaded(User user) {
        if(user.getEmail().equals(email) && user.getPassword().equals(password)){
            Intent mainMenuActivity = new Intent(LoginActivity.this, MainMenuActivity.class);
            startActivity(mainMenuActivity);
        }else{
            Toast.makeText(this, "Login Failed",Toast.LENGTH_LONG).show();
        }
    }
}