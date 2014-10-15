package com.example.spawneddeliveryservice.models;

import org.json.JSONObject;

import java.util.Date;

public class ActiveTransportDataModel {
    public int Id;
    public Date ArrivalDate;
    public String FromTown;
    public String ToTown;

    public static ActiveTransportDataModel FromModel(String json){
        try {
            JSONObject jObject = new JSONObject(json);
            ActiveTransportDataModel returnedModel = new ActiveTransportDataModel();

            returnedModel.Id = jObject.getInt("Id");
            returnedModel.ArrivalDate = Constants.formatter.parse(jObject.getString("ArrivalDate"));
            returnedModel.FromTown = jObject.getString("FromTown");
            returnedModel.ToTown = jObject.getString("ToTown");

            return returnedModel;
        }
        catch (Exception e){
            return null;
        }
    }
}
