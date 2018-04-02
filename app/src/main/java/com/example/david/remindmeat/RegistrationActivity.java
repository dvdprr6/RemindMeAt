package com.example.david.remindmeat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.david.remindmeat.implementation.UserImplementation;
import com.example.david.remindmeat.model.User;

public class RegistrationActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
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


        final UserImplementation userImplementation = new UserImplementation(this);

        User user = new User();
        user.setUserId(null);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);

        userImplementation.createUser(user);

    }

    public void loginScreen(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}
