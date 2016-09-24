package com.dahlstore.dnote5;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Context in which this database exists.
    private Context mContext;

    // Database version.
    public static final int DATABASE_VERSION = 1;

    // Database name.
    public static final String DATABASE_NAME = "ImageExample";

    // Table names.
    public static final String TABLE_CUSTOMERS = "customers";

    private final static String TAG = DatabaseHelper.class.getSimpleName();


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CUSTOMER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMERS);

    }

    // Command to create a table of clients.
    private static final String CREATE_CUSTOMER_TABLE = "CREATE TABLE " + TABLE_CUSTOMERS + " ("
            + Constants.COLUMN_CUSTOMER_ID + " INTEGER PRIMARY KEY, "
            + Constants.COLUMN_IMAGE_PATH + " TEXT, "
            + Constants.COLUMN_NAME + " TEXT, "
            + Constants.COLUMN_PHONE + " TEXT, "
            + Constants.COLUMN_EMAIL + " TEXT, "
            + Constants.COLUMN_STREET + " TEXT, "
            + Constants.COLUMN_CITY + " TEXT, "
            + Constants.COLUMN_STATE + " TEXT, "
            + Constants.COLUMN_ZIP_CODE + " TEXT)";

    // Database lock to prevent conflicts.
    public static final Object[] databaseLock = new Object[0];


}