package com.example.spawneddeliveryservice.appData;

import android.content.Context;

import com.example.spawneddeliveryservice.models.Stats;
import com.example.spawneddeliveryservice.models.Town;

import java.util.List;

public class DAO {
    private static TownsDataSource mTownsDb;
    private static StatsDataSource mStatsDb;

    public static List<Town> getTowns(Context context) {
        if (mTownsDb == null) {
            mTownsDb = new TownsDataSource(context);
            mTownsDb.open();
        }

        return mTownsDb.getAllTowns();
    }

    public static Stats getStats(Context context) {
        if (mStatsDb == null) {
            mStatsDb = new StatsDataSource(context);
            mStatsDb.open();
        }

        return mStatsDb.getAllStats().get(0);
    }
}
