package com.example.spawneddeliveryservice.models;

import org.json.JSONObject;

import java.util.Date;

public class FoundTransportDataModel {
    public int id;
    public int space;
    public int kilograms;
    public String fromTown;
    public String toTown;
    public Date departure;
    public Date arrival;

    public static FoundTransportDataModel FromModel(String json){
        try {
            JSONObject jObject = new JSONObject(json);
            FoundTransportDataModel returnedModel = new FoundTransportDataModel();

            returnedModel.id = jObject.getInt("Id");
            returnedModel.space = jObject.getInt("Space");
            returnedModel.kilograms = jObject.getInt("Kilograms");
            returnedModel.fromTown = jObject.getString("FromTown");
            returnedModel.toTown = jObject.getString("ToTown");
            String dateAsStringArrival = jObject.getString("Arrival");
            try {
                returnedModel.arrival = ConstantsDataModels.getDateFormatter().parse(dateAsStringArrival);
            }
            catch (Exception e) {
                returnedModel.arrival = ConstantsDataModels.getNoMsDateFormatter().parse(dateAsStringArrival);
            }
            String dateAsStringDeparture = jObject.getString("Departure");
            try {
                returnedModel.departure = ConstantsDataModels.getDateFormatter().parse(dateAsStringDeparture);
            }
            catch (Exception e) {
                returnedModel.departure = ConstantsDataModels.getNoMsDateFormatter().parse(dateAsStringDeparture);
            }

            return returnedModel;
        }
        catch (Exception e){
            return null;
        }
    }
}
