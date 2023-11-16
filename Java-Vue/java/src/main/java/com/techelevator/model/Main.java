package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Main {

    private double temp;

    @JsonProperty("feels_like")
    private double feelsLike;

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(double feelsLike) {
        this.feelsLike = feelsLike;
    }
}
