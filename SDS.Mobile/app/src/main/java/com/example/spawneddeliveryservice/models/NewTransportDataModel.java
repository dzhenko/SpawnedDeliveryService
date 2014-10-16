package com.example.spawneddeliveryservice.models;

public class NewTransportDataModel {
    public String fromTown;
    public String toTown;
    public int availableSpace;
    public int availableKilograms;
    public String arrival;//2015-03-17 format;
    public String departure;//2015-03-17 format
    public String driverName;
    public String driverPhone;
    public String additionalContact;

    public NewTransportDataModel(String fromTown, String toTown, int availableSpace, int availableKilograms,
                                 String arrival, String departure, String driverName, String driverPhone, String additionalContact) {
        this.fromTown = fromTown;
        this.toTown = toTown;
        this.availableSpace = availableSpace;
        this.availableKilograms = availableKilograms;
        this.arrival = arrival;
        this.departure = departure;
        this.driverName = driverName;
        this.driverPhone = driverPhone;
        this.additionalContact = additionalContact;
    }
}
