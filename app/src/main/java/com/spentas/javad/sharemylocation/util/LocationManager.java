package com.spentas.javad.sharemylocation.util;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/**
 * Created by javad on 10/16/2015.
 */
public class LocationManager implements ILocationManager,LocationListener {

    @Override
    public Location Location() {
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        double speed = location.getSpeed(); //spedd in meter/minute
        speed = (speed*3600)/1000;      // speed in km/minute
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
