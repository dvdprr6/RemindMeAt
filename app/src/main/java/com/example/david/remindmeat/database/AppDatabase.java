package com.example.david.remindmeat.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.david.remindmeat.dao.UserDao;
import com.example.david.remindmeat.model.ReminedItem;
import com.example.david.remindmeat.model.User;

@Database(entities = {User.class, ReminedItem.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;
    private static final String DB_FILE_NAME = "remindmeat.db";

    AppDatabase(){}

    public static synchronized AppDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, DB_FILE_NAME).build();
        }

        return instance;
    }

    public abstract UserDao getUserDao();

}

