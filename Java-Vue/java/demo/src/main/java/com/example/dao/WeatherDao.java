package com.example.dao;

import com.example.models.LatLon;
import com.example.models.User;
import com.example.models.Weather;

public interface WeatherDao {
    Weather createWeather(Weather weather, User user, LatLon latLon);
    Weather getWeatherByUser(User user);
    Weather getWeatherByLatLon(LatLon latLon);
    Weather getWeatherByWeatherId(int weatherId);
    Weather getWeatherByLatLonAndUser(LatLon latLon, User user);
    Weather updateWeather(Weather weather, User user, LatLon latLon);
    void deleteWeather(Weather weather);

}
