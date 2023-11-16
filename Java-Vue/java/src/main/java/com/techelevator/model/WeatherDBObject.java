package com.techelevator.model;

public class WeatherDBObject {
    private int id;
    private int userId;
    private String zipcode;
    private String main;
    private String description;
    private double temperature;

    public WeatherDBObject() {
    }

    public WeatherDBObject(int id, int userId, String zipcode, String main, String description, double temperature) {
        this.id = id;
        this.userId = userId;
        this.zipcode = zipcode;
        this.main = main;
        this.description = description;
        this.temperature = temperature;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getZipcode() {
        return zipcode;
    }
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getMain() {
        return main;
    }
    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public double getTemperature() {
        return temperature;
    }
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "WeatherDBObject{" +
                "id=" + id +
                ", userId=" + userId +
                ", zipcode='" + zipcode + '\'' +
                ", main='" + main + '\'' +
                ", description='" + description + '\'' +
                ", temperature=" + temperature +
                '}';
    }


}
