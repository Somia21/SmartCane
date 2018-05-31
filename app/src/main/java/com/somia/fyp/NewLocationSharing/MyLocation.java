package com.somia.fyp.NewLocationSharing;

/**
 * Created by Somia on 4/24/2018.
 */

public class MyLocation {
    private double lng;
    private double lat;

    public MyLocation() {
    }

    public MyLocation(double lng, double lat) {
        this.lng = lng;
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
}
