package com.example.david.remindmeat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.david.remindmeat.dao.UserDao;
import com.example.david.remindmeat.dao.UserItemDao;
import com.example.david.remindmeat.model.UserItem;
import com.example.david.remindmeat.utils.Constants;

public class RegistrationActivity extends AppCompatActivity{
    private UserDao userItemDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        userItemDao = new UserItemDao(this);
    }

    public void registerUser(View view){
        EditText editTextFirstName = findViewById(R.id.firstname);
        EditText editTextLastName = findViewById(R.id.lastname);
        EditText editTextEmail = findViewById(R.id.email);
        EditText editTextPassword = findViewById(R.id.password);

        String firstName = editTextFirstName.getText().toString();
        String lastName = editTextLastName.getText().toString();
        String email = editTextEmail.getText().toString().toLowerCase();
        String password = editTextPassword.getText().toString();

        if(!firstName.equals("") && !lastName.equals("") && !email.equals("") && !password.equals("")){
            UserItem userItem = new UserItem();
            userItem.setId(null);
            userItem.setFirstName(firstName);
            userItem.setLastName(lastName);
            userItem.setPassword(password);
            userItem.setEmail(email);

            registerUser(userItem);
        }else {
            Toast.makeText(this, Constants.TEXT_EDIT_MISSING_INPUT_FEILDS, Toast.LENGTH_LONG).show();
        }

    }

    public void loginScreen(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void registerUser(UserItem userItem){
        if(userItemDao.insert(userItem) > -1){
            Intent loginActivityIntent = new Intent(this, LoginActivity.class);
            startActivity(loginActivityIntent);
        }else{
            Toast.makeText(this, Constants.LOGIN_FAILED_EMAIL, Toast.LENGTH_LONG).show();
        }
    }

}
