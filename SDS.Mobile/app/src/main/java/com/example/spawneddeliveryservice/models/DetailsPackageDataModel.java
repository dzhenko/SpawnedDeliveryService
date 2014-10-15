package com.example.spawneddeliveryservice.models;

import org.json.JSONObject;

import java.util.Date;

public class DetailsPackageDataModel {
    public int Id;
    public int TransportId;
    public String PictureURL;
    public double Price;
    // in square meters
    public int Space;
    public int Kilograms;
    public String FromTown;
    public String ToTown;
    public Date Deadline;
    public String Notes;
    public String OwnerName;
    public String OwnerNumber;

    public static DetailsPackageDataModel FromModel(String json){
        try {
            JSONObject jObject = new JSONObject(json);
            DetailsPackageDataModel returnedModel = new DetailsPackageDataModel();

            returnedModel.Id = jObject.getInt("Id");
            String transportIdString = jObject.getString("TransportId");
            if (transportIdString!=null && transportIdString.compareTo("") != 0) {
                returnedModel.TransportId = Integer.getInteger(transportIdString);
            }
            returnedModel.PictureURL = jObject.getString("PictureURL");
            returnedModel.Price = jObject.getDouble("Price");
            returnedModel.Space = jObject.getInt("Space");
            returnedModel.Kilograms = jObject.getInt("Kilograms");
            returnedModel.FromTown = jObject.getString("FromTown");
            returnedModel.ToTown = jObject.getString("ToTown");
            returnedModel.Deadline = Constants.formatter.parse(jObject.getString("Deadline"));
            returnedModel.Notes = jObject.getString("Notes");
            returnedModel.OwnerName = jObject.getString("OwnerName");
            returnedModel.OwnerNumber = jObject.getString("OwnerNumber");

            return returnedModel;
        }
        catch (Exception e){
            return null;
        }
    }
}
