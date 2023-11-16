package com.techelevator.dao;

import org.springframework.stereotype.Component;

import com.techelevator.model.LatLon;
import com.techelevator.model.User;
import com.techelevator.model.Weather;
import com.techelevator.model.WeatherDBObject;
import com.techelevator.model.WeatherObject;


public interface WeatherDao {

    WeatherDBObject createWeather(WeatherObject weather, LatLon latLon, User user);


    Weather getWeatherByUser(User user);
}
