package com.example.david.remindmeat.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_FILE_NAME = "remindmeat.db";
    public static final int DB_VERSION = 1;

    private static DBHelper singleton;

    private DBHelper(Context context){
        super(context, DB_FILE_NAME, null, DB_VERSION);
    }

    public static synchronized DBHelper getSingleton(Context context){
        if(singleton == null){
            singleton = new DBHelper(context.getApplicationContext());
        }

        return singleton;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
