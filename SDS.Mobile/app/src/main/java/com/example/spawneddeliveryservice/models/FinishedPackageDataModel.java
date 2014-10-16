package com.example.spawneddeliveryservice.models;

import org.json.JSONObject;

import java.util.Date;

public class FinishedPackageDataModel {
    public int id;
    public String fromTown;
    public String toTown;
    public Date arrival;
    public String driverName;
    public String driverPhone;

    public static FinishedPackageDataModel FromModel(String json){
        try {
            JSONObject jObject = new JSONObject(json);
            FinishedPackageDataModel returnedModel = new FinishedPackageDataModel();

            returnedModel.id = jObject.getInt("Id");
            returnedModel.fromTown = jObject.getString("FromTown");
            returnedModel.toTown = jObject.getString("ToTown");
            returnedModel.arrival = ConstantsDataModels.getDateFormatter().parse(jObject.getString("Arrival"));
            returnedModel.driverName = jObject.getString("DriverName");
            returnedModel.driverPhone = jObject.getString("DriverPhone");

            return returnedModel;
        }
        catch (Exception e){
            return null;
        }
    }
}
