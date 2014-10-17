package com.example.spawneddeliveryservice.models;

import org.json.JSONObject;

import java.util.Date;

public class OwnPackageDataModel {
    public int id;
    public String fromTown;
    public String toTown;
    public Date deadline;

    public static OwnPackageDataModel FromModel(String json){
        try {
            JSONObject jObject = new JSONObject(json);
            OwnPackageDataModel returnedModel = new OwnPackageDataModel();

            returnedModel.id = jObject.getInt("Id");
            returnedModel.fromTown = jObject.getString("FromTown");
            returnedModel.toTown = jObject.getString("ToTown");
            String dateAsString = jObject.getString("Deadline");
            try {
                returnedModel.deadline = ConstantsDataModels.getDateFormatter().parse(dateAsString);
            }
            catch (Exception e) {
                returnedModel.deadline = ConstantsDataModels.getNoMsDateFormatter().parse(dateAsString);
            }

            return returnedModel;
        }
        catch (Exception e){
            return null;
        }
    }
}
