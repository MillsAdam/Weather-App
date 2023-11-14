package com.example.models;

public class LatLon {
    private String lat; // latitude
    private String lon; // longitude
    private String name; // city name

    // not necessary, java will create a default constructor for us
    public LatLon() {}

    public LatLon(String lat, String lon, String name) {
        this.lat = lat;
        this.lon = lon;
        this.name = name;
    }

    public String getLat() { return lat; }
    public void setLat(String lat) { this.lat = lat; }

    public String getLon() { return lon; }
    public void setLon(String lon) { this.lon = lon; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return String.format("LatLon[lat=%s, lon=%s, name=%s]", lat, lon, name);
    }
}
