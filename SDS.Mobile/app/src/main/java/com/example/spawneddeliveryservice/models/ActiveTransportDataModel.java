package com.example.spawneddeliveryservice.models;

import org.json.JSONObject;

import java.util.Date;

public class ActiveTransportDataModel {
    public int id;
    public Date arrivalDate;
    public String fromTown;
    public String toTown;

    public static ActiveTransportDataModel FromModel(String json){
        try {
            JSONObject jObject = new JSONObject(json);
            ActiveTransportDataModel returnedModel = new ActiveTransportDataModel();

            returnedModel.id = jObject.getInt("Id");
            String dateAsStringDeparture = jObject.getString("ArrivalDate");
            try {
                returnedModel.arrivalDate = ConstantsDataModels.getDateFormatter().parse(dateAsStringDeparture);
            }
            catch (Exception e) {
                returnedModel.arrivalDate = ConstantsDataModels.getNoMsDateFormatter().parse(dateAsStringDeparture);
            }
            returnedModel.fromTown = jObject.getString("FromTown");
            returnedModel.toTown = jObject.getString("ToTown");

            return returnedModel;
        }
        catch (Exception e){
            return null;
        }
    }
}
