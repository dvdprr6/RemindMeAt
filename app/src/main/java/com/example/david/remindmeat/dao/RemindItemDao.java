package com.example.david.remindmeat.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.david.remindmeat.database.DatabaseHelper;
import com.example.david.remindmeat.model.RemindItem;

import java.util.List;

public class RemindItemDao extends RemindDao {
    private SQLiteOpenHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;

    public RemindItemDao(Context context){
        databaseHelper = DatabaseHelper.getInstance(context);
    }

    @Override
    public List<RemindItem> select() {
        return null;
    }

    @Override
    public RemindItem selectById(String id) {
        return null;
    }

    @Override
    public long insert(RemindItem remindItem) {
        return 0;
    }

    @Override
    public void update(RemindItem remindItem) {

    }

    @Override
    public void delete(RemindItem remindItem) {

    }
}
