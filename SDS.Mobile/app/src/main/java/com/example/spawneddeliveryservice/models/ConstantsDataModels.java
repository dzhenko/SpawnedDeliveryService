package com.example.spawneddeliveryservice.models;

import java.text.SimpleDateFormat;

public class ConstantsDataModels {
    private static SimpleDateFormat mFormatter;

    public static SimpleDateFormat getDateFormatter() {
        if (mFormatter == null) {
            mFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S");
            mFormatter.setLenient(false);
        }

        return mFormatter;
    }
}
