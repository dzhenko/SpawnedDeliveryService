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
    public String ownerPhone;
    public String additionalContact;

    public static DetailsTransportPackageDataModel FromModel(String json){
        try {
            JSONObject jObject = new JSONObject(json);
            DetailsTransportPackageDataModel returnedModel = new DetailsTransportPackageDataModel();

            returnedModel.id = jObject.getInt("Id");
            returnedModel.price = jObject.getDouble("Price");
            returnedModel.space = jObject.getInt("Space");
            returnedModel.kilograms = jObject.getInt("Kilograms");
            String dateAsString = jObject.getString("Deadline");
            try {
                returnedModel.deadline = ConstantsDataModels.getDateFormatter().parse(dateAsString);
            }
            catch (Exception e) {
                returnedModel.deadline = ConstantsDataModels.getNoMsDateFormatter().parse(dateAsString);
            }
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
