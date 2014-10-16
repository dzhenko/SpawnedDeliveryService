package com.example.spawneddeliveryservice.models;

public class NewPackageDataModel {
    public String Picture;
    public double Price;
    public int Space;
    public int Kilograms;
    public String FromTown;
    public String ToTown;
    public String Deadline;//2015-03-17 format
    public String Notes;

    public NewPackageDataModel(String base64Picture, double price, int space, int kilograms, String fromTown, String toTown, String deadline, String notes) {
        this.Picture  = base64Picture;
        this.Price = price;
        this.Space = space;
        this.Kilograms = kilograms;
        this.FromTown = fromTown;
        this.ToTown = toTown;
        this.Deadline = deadline;
        this.Notes = notes;
    }
}
