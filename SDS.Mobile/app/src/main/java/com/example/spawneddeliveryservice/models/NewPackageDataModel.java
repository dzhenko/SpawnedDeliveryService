package com.example.spawneddeliveryservice.models;

public class NewPackageDataModel {
    public String picture;
    public double price;
    public int space;
    public int kilograms;
    public String fromTown;
    public String toTown;
    public String deadline;//2015-03-17 format
    public String notes;
    public String additionalContact;

    public NewPackageDataModel(String base64Picture, double price, int space, int kilograms,
                               String fromTown, String toTown, String deadline, String notes, String additionalContact) {
        this.picture = base64Picture;
        this.price = price;
        this.space = space;
        this.kilograms = kilograms;
        this.fromTown = fromTown;
        this.toTown = toTown;
        this.deadline = deadline;
        this.notes = notes;
        this.additionalContact = additionalContact;
    }
}
