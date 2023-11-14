package com.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Main {
    private double temp;
    @JsonProperty("feels_like")
    private double feelsLike;

    public Main() {}

    public Main(double temp, double feelsLike) {
        this.temp = temp;
        this.feelsLike = feelsLike;
    }

    public double getTemp() { return temp; }
    public void setTemp(double temp) { this.temp = temp; }

    public double getFeelsLike() { return feelsLike; }
    public void setFeelsLike(double feelsLike) { this.feelsLike = feelsLike; }

    @Override
    public String toString() {
        return String.format("Main[temp=%s, feelsLike=%s]", temp, feelsLike);
    }
}
