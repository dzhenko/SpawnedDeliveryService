package com.example.spawneddeliveryservice.models;

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
    public String driverNumber;
    public ArrayList<DetailsTransportPackageDataModel> packages;


}
