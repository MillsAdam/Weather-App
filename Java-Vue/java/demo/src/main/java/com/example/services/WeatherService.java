package com.example.services;

import org.springframework.web.client.RestTemplate;
import com.example.models.LatLon;
import com.example.models.WeatherObject;

import io.github.cdimascio.dotenv.Dotenv;

public class WeatherService {
    private RestTemplate restTemplate = new RestTemplate();
    private static final String API_URL = "http://api.openweathermap.org/geo/1.0/zip";

    private static final Dotenv dotenv = Dotenv.load();
    private static final String API_KEY = dotenv.get("OPEN_WEATHER_API_KEY");

    public LatLon getLatLon(String zipCode) {
        String url = API_URL + "?zip=" + zipCode + "&appid=" + API_KEY;
        return restTemplate.getForObject(url, LatLon.class);
    }

    public WeatherObject getWeather(LatLon latLon) {
        String url = "https://api.openweathermap.org/data/2.5/weather?lat=" + latLon.getLat() + "&lon=" + latLon.getLon() + "&units=imperial&appid=" + API_KEY;
        return restTemplate.getForObject(url, WeatherObject.class);
    }
}
