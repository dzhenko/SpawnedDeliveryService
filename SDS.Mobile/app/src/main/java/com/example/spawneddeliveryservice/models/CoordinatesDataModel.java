package com.example.spawneddeliveryservice.models;

import org.json.JSONObject;

public class CoordinatesDataModel {
    public double latitude;
    public double longitude;

    public static CoordinatesDataModel FromModel(String json){
        try {
            JSONObject jObject = new JSONObject(json);
            CoordinatesDataModel returnedModel = new CoordinatesDataModel();

            returnedModel.latitude = jObject.getDouble("Latitude");
            returnedModel.longitude = jObject.getDouble("Longitude");

            return returnedModel;
        }
        catch (Exception e){
            return null;
        }
    }
}
