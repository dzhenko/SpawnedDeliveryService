package com.example.spawneddeliveryservice.models;

import java.text.SimpleDateFormat;

public class ConstantsDataModels {
    private static SimpleDateFormat formatter;

    public static SimpleDateFormat getDateFormatter() {
        if (formatter == null) {
            formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S");
            formatter.setLenient(false);
        }

        return formatter;
    }
}
