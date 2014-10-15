package com.example.spawneddeliveryservice.appData;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_TOWNS = "towns";
    public static final String COLUMN_TOWNS_ID = "_id";
    public static final String COLUMN_TOWNS_NAME = "name";

    public static final String TABLE_STATS = "stats";
    public static final String COLUMN_STATS_ID = "_id";
    public static final String COLUMN_STATS_USERS = "users";
    public static final String COLUMN_STATS_PACKAGES = "packages";
    public static final String COLUMN_STATS_TRANSPORTS = "transports";
    public static final String COLUMN_STATS_UPDATED = "updated";

    private static final String DATABASE_NAME = "SDS.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE_TOWNS = "create table "
            + TABLE_TOWNS + "(" + COLUMN_TOWNS_ID
            + " integer primary key autoincrement, " + COLUMN_TOWNS_NAME
            + " text not null);";

    private static final String DATABASE_CREATE_STATS = "create table "
            + TABLE_STATS + "(" + COLUMN_STATS_ID + " integer primary key autoincrement, " +
                                  COLUMN_STATS_USERS + " text not null, " +
                                  COLUMN_STATS_PACKAGES + " text not null, " +
                                  COLUMN_STATS_TRANSPORTS + " text not null, " +
                                  COLUMN_STATS_UPDATED  + " date not null);";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE_TOWNS);
        database.execSQL(DATABASE_CREATE_STATS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_CREATE_TOWNS);
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_CREATE_STATS);
        onCreate(db);
    }
}
