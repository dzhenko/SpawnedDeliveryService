package com.example.spawneddeliveryservice.models;

import org.json.JSONObject;

import java.util.Date;

public class FoundPackageDataModel {
    public int id;
    public double price;
    // in square meters
    public int space;
    public int kilograms;
    public String fromTown;
    public String toTown;
    public Date deadline;

    public static FoundPackageDataModel FromModel(String json){
        try {
            JSONObject jObject = new JSONObject(json);
            FoundPackageDataModel returnedModel = new FoundPackageDataModel();

            returnedModel.id = jObject.getInt("Id");
            returnedModel.price = jObject.getDouble("Price");
            returnedModel.space = jObject.getInt("Space");
            returnedModel.kilograms = jObject.getInt("Kilograms");
            returnedModel.fromTown = jObject.getString("FromTown");
            returnedModel.toTown = jObject.getString("ToTown");
            returnedModel.deadline = ConstantsDataModels.getDateFormatter().parse(jObject.getString("Deadline"));

            return returnedModel;
        }
        catch (Exception e){
            return null;
        }
    }
}
