package com.example.spawneddeliveryservice.models;

import org.json.JSONObject;

import java.util.Date;

public class PendingTransportDataModel {
    public int id;
    public Date deadline;
    public String fromTown;
    public String toTown;

    public static PendingTransportDataModel FromModel(String json){
        try {
            JSONObject jObject = new JSONObject(json);
            PendingTransportDataModel returnedModel = new PendingTransportDataModel();

            returnedModel.id = jObject.getInt("Id");
            returnedModel.fromTown = jObject.getString("FromTown");
            returnedModel.toTown = jObject.getString("ToTown");
            returnedModel.deadline = ConstantsDataModels.formatter.parse(jObject.getString("Deadline"));

            return returnedModel;
        }
        catch (Exception e){
            return null;
        }
    }
}
