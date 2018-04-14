package com.example.david.remindmeat.table;

public class RemindTable {
    public static final String TABLE_REMIND = "REMIND";
    public static final String COLUMN_ID = "remind_id";
    public static final String COLUMN_REF_USER_ID = "user_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_LONGITUDE = "longitude";
    public static final String COLUMN_LATITUDE = "latitude";

    public static final String[] ALL_COLUMNS = {
            COLUMN_ID,
            COLUMN_REF_USER_ID,
            COLUMN_TITLE,
            COLUMN_DESCRIPTION,
            COLUMN_LONGITUDE,
            COLUMN_LATITUDE,
    };

    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_REMIND + "(" +
                    COLUMN_ID + " TEXT PRIMARY KEY," +
                    COLUMN_TITLE + " TEXT," +
                    COLUMN_DESCRIPTION + " TEXT," +
                    COLUMN_LONGITUDE + " REAL," +
                    COLUMN_LATITUDE + " REAL," +
                    COLUMN_REF_USER_ID + " REFERENCES " + UserTable.TABLE_USERS + "(" + UserTable.COLUMN_ID + ")" +
                    ");";

    public static final String SQL_DELETE =
            "DROP TABLE " + TABLE_REMIND;
}
