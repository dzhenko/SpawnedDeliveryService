package com.example.spawneddeliveryservice.appData;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.spawneddeliveryservice.models.Town;

import java.util.ArrayList;
import java.util.List;

public class TownsDataSource {
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;
    private String[] allColumns = { SQLiteHelper.COLUMN_TOWNS_ID,
            SQLiteHelper.COLUMN_TOWNS_NAME };

    public TownsDataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Town createTown(String name) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_TOWNS_NAME, name);
        long insertId = database.insert(SQLiteHelper.TABLE_TOWNS, null,
                values);
        Cursor cursor = database.query(SQLiteHelper.TABLE_TOWNS,
                allColumns, SQLiteHelper.COLUMN_TOWNS_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Town newTown = cursorToTown(cursor);
        cursor.close();
        return newTown;
    }

    public void deleteTown(Town town) {
        long id = town.getId();
        database.delete(SQLiteHelper.TABLE_TOWNS, SQLiteHelper.COLUMN_TOWNS_ID
                + " = " + id, null);
    }

    public List<Town> getAllTowns() {
        List<Town> towns = new ArrayList<Town>();

        Cursor cursor = database.query(SQLiteHelper.TABLE_TOWNS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Town town = cursorToTown(cursor);
            towns.add(town);
            cursor.moveToNext();
        }

        cursor.close();
        return towns;
    }

    private Town cursorToTown(Cursor cursor) {
        Town town = new Town();
        town.setId(cursor.getLong(0));
        town.setName(cursor.getString(1));
        return town;
    }
}

