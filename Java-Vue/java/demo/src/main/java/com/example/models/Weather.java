package com.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Weather {
    private String main;
    private String description;
    private double temp;
    @JsonProperty("feels_like")
    private double feelsLike;

    public Weather(String main, String description, double temp, double feelsLike) {
        this.main = main;
        this.description = description;
        this.temp = temp;
        this.feelsLike = feelsLike;
    }

    public String getMain() { return main; }
    public void setMain(String main) { this.main = main; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getTemp() { return temp; }
    public void setTemp(double temp) { this.temp = temp; }

    public double getFeelsLike() { return feelsLike; }
    public void setFeelsLike(double feelsLike) { this.feelsLike = feelsLike; }


    @Override
    public String toString() {
        return String.format("Weather[main=%s, description=%s, temp=%s, feelsLike=%s]", main, description, temp, feelsLike);
    }
}
