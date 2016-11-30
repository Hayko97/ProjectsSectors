package me.infomapwork.mapwork.dto.models;

/**
 * Created by H.Harutyunyan on 11/24/2016.
 */

public class TaskLocation {
    private double latitude;
    private double longitude;

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
