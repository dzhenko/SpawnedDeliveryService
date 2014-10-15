package com.example.spawneddeliveryservice.models;

import java.util.ArrayList;
import java.util.Date;

public class DetailsTransportDataModel {
    public int Id;
    public String FromTown;
    public String ToTown;
    public int AvailableSpace;
    public int AvailableKilograms;
    public double TotalPrice;
    public Date Departure;
    public Date Arrival;
    public String DriverName;
    public String DriverNumber;
    public ArrayList<DetailsTransportPackageDataModel> Packages;
}
