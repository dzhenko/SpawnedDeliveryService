package com.example.spawneddeliveryservice.models;

import org.json.JSONObject;

import java.util.Date;

public class DetailsTransportPackageDataModel {
    public int id;
    public double price;
    public int space;
    public int kilograms;
    public Date deadline;
    public String ownerName;
    public String ownerNumber;

    public static DetailsTransportPackageDataModel FromModel(String json){
        try {
            JSONObject jObject = new JSONObject(json);
            DetailsTransportPackageDataModel returnedModel = new DetailsTransportPackageDataModel();

            returnedModel.id = jObject.getInt("Id");
            String transportIdString = jObject.getString("TransportId");
            if (transportIdString!=null && transportIdString.compareTo("") != 0) {
                returnedModel.transportId = Integer.getInteger(transportIdString);
            }
            returnedModel.pictureURL = jObject.getString("PictureURL");
            returnedModel.price = jObject.getDouble("Price");
            returnedModel.space = jObject.getInt("Space");
            returnedModel.kilograms = jObject.getInt("Kilograms");
            returnedModel.fromTown = jObject.getString("FromTown");
            returnedModel.toTown = jObject.getString("ToTown");
            returnedModel.deadline = Constants.formatter.parse(jObject.getString("Deadline"));
            returnedModel.notes = jObject.getString("Notes");
            returnedModel.ownerName = jObject.getString("OwnerName");
            returnedModel.ownerNumber = jObject.getString("OwnerNumber");

            return returnedModel;
        }
        catch (Exception e){
            return null;
        }
    }
}
