package com.example.spawneddeliveryservice.models;

public class NewTransportDataModel {
    public String FromTown;
    public String ToTown;
    public int AvailableSpace;
    public int AvailableKilograms;
    public String Arrival;//2015-03-17 format;
    public String Departure;//2015-03-17 format
    public String DriverName;
    public String DriverPhone;

    public NewTransportDataModel(String fromTown, String toTown, int availableSpace, int availableKilograms, String arrival, String departure, String driverName, String driverPhone) {
        this.FromTown = fromTown;
        this.ToTown = toTown;
        this.AvailableSpace = availableSpace;
        this.AvailableKilograms = availableKilograms;
        this.Arrival = arrival;
        this.Departure = departure;
        this.DriverName = driverName;
        this.DriverPhone = driverPhone;
    }
}
