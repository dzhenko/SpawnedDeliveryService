package com.example.spawneddeliveryservice.models;

import org.json.JSONObject;

import java.util.Date;

public class DetailsPackageDataModel {
    public int id;
    public int transportId;
    public String pictureURL;
    public double price;
    // in square meters
    public int space;
    public int kilograms;
    public String fromTown;
    public String toTown;
    public Date deadline;
    public String notes;
    public String ownerName;
    public String ownerPhone;
    public String additionalContact;

    public static DetailsPackageDataModel FromModel(String json){
        try {
            JSONObject jObject = new JSONObject(json);
            DetailsPackageDataModel returnedModel = new DetailsPackageDataModel();

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
            String dateAsStringDeparture = jObject.getString("Deadline");
            try {
                returnedModel.deadline = ConstantsDataModels.getDateFormatter().parse(dateAsStringDeparture);
            }
            catch (Exception e) {
                returnedModel.deadline = ConstantsDataModels.getNoMsDateFormatter().parse(dateAsStringDeparture);
            }
            returnedModel.notes = jObject.getString("Notes");
            returnedModel.ownerName = jObject.getString("OwnerName");
            returnedModel.ownerPhone = jObject.getString("OwnerPhone");
            returnedModel.additionalContact = jObject.getString("AdditionalContact");

            return returnedModel;
        }
        catch (Exception e){
            return null;
        }
    }
}
