package com.example.spawneddeliveryservice.models;

import java.text.SimpleDateFormat;

public class ConstantsDataModels {
    private static SimpleDateFormat mFormatter;
    private static SimpleDateFormat mNoMsFormatter;

    public static SimpleDateFormat getDateFormatter() {
        if (mFormatter == null) {
            mFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S");
            mFormatter.setLenient(false);
        }

        return mFormatter;
    }

    public static SimpleDateFormat getNoMsDateFormatter() {
        if (mNoMsFormatter == null) {
            mNoMsFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            mNoMsFormatter.setLenient(false);
        }

        return mNoMsFormatter;
    }
}
