package com.example.david.remindmeat.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.david.remindmeat.database.DatabaseHelper;
import com.example.david.remindmeat.model.Item;
import com.example.david.remindmeat.model.RemindItem;
import com.example.david.remindmeat.table.RemindTable;

import java.util.ArrayList;
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
        ContentValues values = remindItem.toValues();
        sqLiteDatabase = databaseHelper.getWritableDatabase();

        long insertResult = sqLiteDatabase.insert(RemindTable.TABLE_REMIND, null, values);

        return insertResult;
    }

    @Override
    public void update(RemindItem remindItem) {

    }

    @Override
    public void delete(RemindItem remindItem) {

    }

    @Override
    public List<Item> findRemindItemsByUserId(String userId) {
        List<Item> remindItems = new ArrayList();
        sqLiteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(
                RemindTable.TABLE_REMIND,
                RemindTable.ALL_COLUMNS,
                RemindTable.COLUMN_REF_USER_ID + "= ?",
                new String[]{userId},
                null, null, null);

        while(cursor.moveToNext()){
            RemindItem remindItem = new RemindItem();
            String id = cursor.getString(cursor.getColumnIndex(RemindTable.COLUMN_ID));
            String title = cursor.getString(cursor.getColumnIndex(RemindTable.COLUMN_TITLE));
            String description = cursor.getString(cursor.getColumnIndex(RemindTable.COLUMN_DESCRIPTION));
            double latitude = cursor.getDouble(cursor.getColumnIndex(RemindTable.COLUMN_LATITUDE));
            double longitude = cursor.getDouble(cursor.getColumnIndex(RemindTable.COLUMN_LONGITUDE));
            userId = cursor.getString(cursor.getColumnIndex(RemindTable.COLUMN_REF_USER_ID));

            remindItem.setId(id);
            remindItem.setTitle(title);
            remindItem.setDescription(description);
            remindItem.setLatitude(latitude);
            remindItem.setLongitude(longitude);
            remindItem.setUserId(userId);

            remindItems.add(remindItem);
        }

        cursor.close();

        return remindItems;
    }
}
