package com.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LatLon {
    @JsonProperty("zip")
    private int zipCode; // zip code
    private String lat; // latitude
    private String lon; // longitude
    private String name; // city name

    // not necessary, java will create a default constructor for us
    public LatLon() {}

    public LatLon(int zipCode, String lat, String lon, String name) {
        this.zipCode = zipCode;
        this.lat = lat;
        this.lon = lon;
        this.name = name;
    }

    public int getZipCode() { return zipCode; }
    public void setZipCode(int zipCode) { this.zipCode = zipCode; }

    public String getLat() { return lat; }
    public void setLat(String lat) { this.lat = lat; }

    public String getLon() { return lon; }
    public void setLon(String lon) { this.lon = lon; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return String.format("LatLon[zipCode=%s, lat=%s, lon=%s, name=%s]", zipCode, lat, lon, name);
    }
}
