package com.example.spawneddeliveryservice.models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class DetailsTransportDataModel {
    public int id;
    public String fromTown;
    public String toTown;
    public int availableSpace;
    public int availableKilograms;
    public double totalPrice;
    public Date departure;
    public Date arrival;
    public String driverName;
    public String driverPhone;
    public String additionalContact;
    public ArrayList<DetailsTransportPackageDataModel> packages;

    public static DetailsTransportDataModel FromModel(String json){
        try {
            JSONObject jObject = new JSONObject(json);
            DetailsTransportDataModel returnedModel = new DetailsTransportDataModel();

            returnedModel.id = jObject.getInt("Id");
            returnedModel.fromTown = jObject.getString("FromTown");
            returnedModel.toTown = jObject.getString("ToTown");
            returnedModel.availableSpace = jObject.getInt("AvailableSpace");
            returnedModel.availableKilograms = jObject.getInt("AvailableKilograms");
            returnedModel.totalPrice = jObject.getInt("TotalPrice");
            String dateAsStringDeparture = jObject.getString("Departure");
            try {
                returnedModel.departure = ConstantsDataModels.getDateFormatter().parse(dateAsStringDeparture);
            }
            catch (Exception e) {
                returnedModel.departure = ConstantsDataModels.getNoMsDateFormatter().parse(dateAsStringDeparture);
            }
            String dateAsStringArrival = jObject.getString("Arrival");
            try {
                returnedModel.arrival = ConstantsDataModels.getDateFormatter().parse(dateAsStringArrival);
            }
            catch (Exception e) {
                returnedModel.arrival = ConstantsDataModels.getNoMsDateFormatter().parse(dateAsStringArrival);
            }
            returnedModel.driverName = jObject.getString("DriverName");
            returnedModel.driverPhone = jObject.getString("DriverPhone");
            returnedModel.additionalContact = jObject.getString("AdditionalContact");

            JSONArray jsonArrayPackages = jObject.getJSONArray("Packages");

            returnedModel.packages = new ArrayList<DetailsTransportPackageDataModel>();

            for(int i=0;i<jsonArrayPackages.length();i++)
            {
                DetailsTransportPackageDataModel modelToAdd = DetailsTransportPackageDataModel.FromModel(jsonArrayPackages.getString(i));
                returnedModel.packages.add(modelToAdd);
            }

            return returnedModel;
        }
        catch (Exception e){
            return null;
        }
    }
}
