package com.example.dao;

import com.example.models.LatLon;
import com.example.models.User;
import com.example.models.Weather;

public class JdbcWeatherDao implements WeatherDao{

    @Override
    public Weather createWeather(Weather weather, User user, LatLon latLon) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createWeather'");
    }

    @Override
    public Weather getWeatherByUser(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWeatherByUser'");
    }

    @Override
    public Weather getWeatherByLatLon(LatLon latLon) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWeatherByLatLon'");
    }

    @Override
    public Weather getWeatherByWeatherId(int weatherId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWeatherByWeatherId'");
    }

    @Override
    public Weather getWeatherByLatLonAndUser(LatLon latLon, User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWeatherByLatLonAndUser'");
    }

    @Override
    public Weather updateWeather(Weather weather, User user, LatLon latLon) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateWeather'");
    }

    @Override
    public void deleteWeather(Weather weather) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteWeather'");
    }
  
}
