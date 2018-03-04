package com.example.david.remindmeat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenuActivity extends AppCompatActivity {
    public final static int SPY = 5;
    public final static String INTENT_RESULT_ACTIVITY_MAIN = "hello";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void remindMeListButton(View view){
        Intent remindMeListIntent = new Intent(MainMenuActivity.this, RemindMeAtListActivity.class);
        startActivityForResult(remindMeListIntent, SPY);
    }

    public void googleMapsButton(View view) {
        Intent googleMapsIntent = new Intent(MainMenuActivity.this, MapsActivity.class);
        startActivityForResult(googleMapsIntent, SPY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == SPY && resultCode == RESULT_OK){

        }
    }
}
