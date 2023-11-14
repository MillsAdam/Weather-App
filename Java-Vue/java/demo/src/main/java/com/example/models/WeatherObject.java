package com.example.models;

public class WeatherObject {
    private Main main;
    private Weather[] weather;

    public WeatherObject() {}

    public WeatherObject(Main main, Weather[] weather) {
        this.main = main;
        this.weather = weather;
    }

    public Main getMain() { return main; }
    public void setMain(Main main) { this.main = main; }

    public Weather[] getWeather() { return weather; }
    public void setWeather(Weather[] weather) { this.weather = weather; }

    @Override
    public String toString() {
        return String.format("WeatherObject[main=%s, weather=%s]", main, weather);
    }
}
