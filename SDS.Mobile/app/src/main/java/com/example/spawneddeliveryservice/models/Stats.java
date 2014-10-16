package com.example.spawneddeliveryservice.models;

import java.util.Date;

public class Stats{
    private long id;
    private int users;
    private int packages;
    private int transports;
    private Date updated;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUsers() {
        return users;
    }

    public void setUsers(int users) {
        this.users = users;
    }

    public int getPackages() {
        return packages;
    }

    public void setPackages(int packages) {
        this.packages = packages;
    }

    public int getTransports() {
        return transports;
    }

    public void setTransports(int transports) {
        this.transports = transports;
    }

    public Date getUpdated(){
        return updated;
    }

    public void setUpdated(Date updated){
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "Home of " + this.getUsers() + " Users, " + this.getPackages()+ " Packages, " + this.getTransports() + " Transports";
    }
}
