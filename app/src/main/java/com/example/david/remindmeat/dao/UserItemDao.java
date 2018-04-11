package com.example.david.remindmeat.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.david.remindmeat.database.DatabaseHelper;
import com.example.david.remindmeat.model.UserItem;
import com.example.david.remindmeat.table.UserTable;
import com.example.david.remindmeat.utils.Constants;

import java.util.List;

public class UserItemDao extends UserDao {
    private SQLiteOpenHelper databaseHelper;
    private SQLiteDatabase sqliteDatabase;

    public UserItemDao(Context context){
        databaseHelper = DatabaseHelper.getInstance(context);
    }

    @Override
    public List<UserItem> select() {
        return null;
    }

    @Override
    public UserItem selectById(String id) {
        return null;
    }

    @Override
    public long insert(UserItem userItem) {
        ContentValues values = userItem.toValues();
        sqliteDatabase = databaseHelper.getWritableDatabase();
        long insertResult;

        try {
            insertResult = sqliteDatabase.insert(UserTable.TABLE_USERS, null, values);
        }catch(SQLiteConstraintException sqlc){
            insertResult = Constants.SQL_INSERT_FAILED;
        }

        return insertResult;
    }

    @Override
    public void update(UserItem userItem) {

    }

    @Override
    public void delete(UserItem userItem) {

    }

    @Override
    public boolean isUser(String email, String password){
        sqliteDatabase = databaseHelper.getReadableDatabase();

        Cursor cursor = sqliteDatabase.query(
                UserTable.TABLE_USERS,
                UserTable.ALL_COLUMNS,
                UserTable.COLUMN_EMAIL + " = ? and " + UserTable.COLUMN_PASSWORD + " = ?",
                new String[]{email, password},
                null, null, null);

        return cursor.moveToNext();
    }
}
