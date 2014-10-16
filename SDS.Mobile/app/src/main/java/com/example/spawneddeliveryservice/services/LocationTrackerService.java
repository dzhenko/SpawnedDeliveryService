package com.example.spawneddeliveryservice.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;

import com.example.spawneddeliveryservice.tasks.LocationChangeTask;

public class LocationTrackerService extends Service {
    private final IBinder mBinder;
    private Locator mLocationListener;
    private LocationTrackerService mLocationTrackerService;
    private LocationManager mLocationManager;

    public LocationTrackerService() {
        this.mBinder = new LocalBinder();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.mLocationListener = new Locator();
        this.mLocationTrackerService = this;
        this.mLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        this.enableUpdates();
    }

    public class LocalBinder extends Binder {
        public LocationTrackerService getService() {
            return mLocationTrackerService;
        }
    }

    public void enableUpdates() {
        if(this.mLocationManager !=null && this.mLocationListener !=null) {
            this.mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 1000, this.mLocationListener);
        }
    }

    public void disableUpdates() {
        if (this.mLocationListener != null) {
            this.mLocationManager.removeUpdates(this.mLocationListener);
        }
    }

    private class Locator implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            try {
                String latitude = Double.toString(location.getLatitude());
                String longitude = Double.toString(location.getLongitude());
                (new LocationChangeTask()).execute(latitude, longitude);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }
}
