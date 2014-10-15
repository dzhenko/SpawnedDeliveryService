package com.example.spawneddeliveryservice.appData;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.spawneddeliveryservice.models.ConstantsDataModels;
import com.example.spawneddeliveryservice.models.Stats;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StatsDataSource {
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;
    private String[] allColumns = { SQLiteHelper.COLUMN_STATS_ID,
            SQLiteHelper.COLUMN_STATS_USERS, SQLiteHelper.COLUMN_STATS_PACKAGES,
            SQLiteHelper.COLUMN_STATS_TRANSPORTS, SQLiteHelper.COLUMN_STATS_UPDATED };

    public StatsDataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Stats createStats(int users, int packages, int transports) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_STATS_USERS, users);
        values.put(SQLiteHelper.COLUMN_STATS_PACKAGES, packages);
        values.put(SQLiteHelper.COLUMN_STATS_TRANSPORTS, transports);
        values.put(SQLiteHelper.COLUMN_STATS_UPDATED, (new Date()).toString());
        long insertId = database.insert(SQLiteHelper.TABLE_STATS, null,
                values);
        Cursor cursor = database.query(SQLiteHelper.TABLE_STATS,
                allColumns, SQLiteHelper.COLUMN_STATS_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Stats newStats = cursorToStats(cursor);
        cursor.close();
        return newStats;
    }

    public void deleteStats(Stats stats) {
        long id = stats.getId();
        database.delete(SQLiteHelper.TABLE_STATS, SQLiteHelper.COLUMN_STATS_ID
                + " = " + id, null);
    }

    public List<Stats> getAllStats() {
        List<Stats> allStats = new ArrayList<Stats>();

        Cursor cursor = database.query(SQLiteHelper.TABLE_STATS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Stats stats = cursorToStats(cursor);
            allStats.add(stats);
            cursor.moveToNext();
        }

        cursor.close();
        return allStats;
    }

    private Stats cursorToStats(Cursor cursor) {
        Stats stats = new Stats();
        stats.setId(cursor.getLong(0));
        stats.setUsers(cursor.getInt(1));
        stats.setPackages(cursor.getInt(2));
        stats.setTransports(cursor.getInt(3));
        try {
            stats.setUpdated(ConstantsDataModels.formatter.parse(cursor.getString(4)));
        } catch (ParseException e) {
            e.printStackTrace();
            stats.setUpdated(new Date());
        }

        return stats;
    }
}
