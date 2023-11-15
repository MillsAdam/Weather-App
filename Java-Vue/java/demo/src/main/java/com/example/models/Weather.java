package com.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Weather {
    private int id;
    private String main;
    private String description;
    private String icon;
    private double temp;
    @JsonProperty("feels_like")
    private double feelsLike;

    public Weather(String main2, String description2, double temp, double feelsLike) {} // changed to match return in WeatherService.java

    public Weather(int id, String main, String description, String icon) {
        this.id = id;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getMain() { return main; }
    public void setMain(String main) { this.main = main; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }

    public double getTemp() { return temp; }
    public void setTemp(double temp) { this.temp = temp; }

    public double getFeelsLike() { return feelsLike; }
    public void setFeelsLike(double feelsLike) { this.feelsLike = feelsLike; }

    // @Override
    // public String toString() {
    //     return String.format("Weather[id=%s, main=%s, description=%s, icon=%s]", id, main, description, icon);
    // }

    @Override
    public String toString() {
        return String.format("Weather[main=%s, description=%s, temp=%s, feelsLike=%s]", main, description, temp, feelsLike);
    }
}
