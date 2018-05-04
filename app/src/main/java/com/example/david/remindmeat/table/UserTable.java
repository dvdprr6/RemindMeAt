package com.example.david.remindmeat.table;

public class UserTable {
    public static final String TABLE_USERS = "USERS";
    public static final String COLUMN_ID = "user_id";
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_LAST_NAME = "last_name";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_EMAIL = "email";

    private static final String CONSTRAINT_UNIQUE_EMAIL = "email_uk";

    public static final String[] ALL_COLUMNS = {
            COLUMN_ID,
            COLUMN_FIRST_NAME,
            COLUMN_LAST_NAME,
            COLUMN_PASSWORD,
            COLUMN_EMAIL
    };

    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_USERS + "(" +
                    COLUMN_ID + " TEXT PRIMARY KEY," +
                    COLUMN_FIRST_NAME + " TEXT," +
                    COLUMN_LAST_NAME + " TEXT," +
                    COLUMN_PASSWORD + " TEXT," +
                    COLUMN_EMAIL + " TEXT," +

                    "CONSTRAINT " + CONSTRAINT_UNIQUE_EMAIL + " UNIQUE (" + COLUMN_EMAIL + "));";

    public static final String SQL_DELETE =
            "DROP TABLE " + TABLE_USERS;
}
