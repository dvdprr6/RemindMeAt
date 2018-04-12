package com.example.david.remindmeat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.david.remindmeat.dao.UserDao;
import com.example.david.remindmeat.dao.UserItemDao;
import com.example.david.remindmeat.global.SharedObject;
import com.example.david.remindmeat.model.UserItem;
import com.example.david.remindmeat.utils.Constants;

public class LoginActivity extends AppCompatActivity{
    private UserDao userItemDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userItemDao = new UserItemDao(this);
    }

    public void loginButton(View view){
        EditText loginEmail = findViewById(R.id.email);
        EditText loginPassword = findViewById(R.id.password);

        String email = loginEmail.getText().toString().toLowerCase();
        String password = loginPassword.getText().toString();

        if(!email.equals("") && !password.equals("")){
            authenticateLogin(email, password);
        }else {
            Toast.makeText(this, Constants.TEXT_EDIT_MISSING_INPUT_FEILDS, Toast.LENGTH_LONG).show();
        }
    }

    public void registerButton(View view){
        Intent registrationActivity = new Intent(LoginActivity.this, RegistrationActivity.class);
        startActivity(registrationActivity);
    }

    private void authenticateLogin(String email, String password){
        if(!email.equals("") && !password.equals("")){
            if(userItemDao.isUser(email, password)){
                UserItem userItem = userItemDao.searchUserByEmail(email);
                SharedObject.getInstance().setSharedUserItemObject(userItem);
                Intent mainMenuActivityIntent = new Intent(this, MainMenuActivity.class);
                startActivity(mainMenuActivityIntent);
            }else{
                Toast.makeText(this, Constants.LOGIN_FAILED_LOGIN_FAILED, Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this, Constants.LOGIN_FAILED_LOGIN_FAILED, Toast.LENGTH_LONG).show();
        }
    }
}