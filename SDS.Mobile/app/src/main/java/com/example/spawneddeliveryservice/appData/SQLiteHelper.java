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
    public static final String COLUMN_STATS_NAME = "name";
    public static final String COLUMN_STATS_NAME = "name";
    public static final String COLUMN_STATS_NAME = "name";
    public static final String COLUMN_STATS_UPDATED = "name";

    private static final String DATABASE_NAME = "SDS.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_COMMENTS + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_COMMENT
            + " text not null);";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
        onCreate(db);
    }

}
