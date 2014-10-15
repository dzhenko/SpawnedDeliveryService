package com.example.spawneddeliveryservice.models;

import org.json.JSONObject;

import java.util.Date;

public class FinishedTransportDataModel {
    public int id;
    public String fromTown;
    public String toTown;
    public Date arrival;
    public String driverName;
    public String driverPhone;

    public static FinishedTransportDataModel FromModel(String json){
        try {
            JSONObject jObject = new JSONObject(json);
            FinishedTransportDataModel returnedModel = new FinishedTransportDataModel();

            returnedModel.id = jObject.getInt("Id");
            returnedModel.fromTown = jObject.getString("FromTown");
            returnedModel.toTown = jObject.getString("ToTown");
            returnedModel.arrival = Constants.formatter.parse(jObject.getString("Arrival"));
            returnedModel.driverName = jObject.getString("DriverName");
            returnedModel.driverPhone = jObject.getString("DriverPhone");

            return returnedModel;
        }
        catch (Exception e){
            return null;
        }
    }
}
