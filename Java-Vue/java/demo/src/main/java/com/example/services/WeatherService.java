package com.example.services;

import org.springframework.web.client.RestTemplate;
import com.example.models.LatLon;

public class WeatherService {
    private RestTemplate restTemplate = new RestTemplate();
    private static final String API_URL = "http://api.openweathermap.org/geo/1.0/zip";
    private static final String API_KEY = "896d7ca05ae3d29c18cd4b2c9ed6a815";

    public LatLon getLatLon(String zipCode) {
        String url = API_URL + "?zip=" + zipCode + "&appid=" + API_KEY;
        return restTemplate.getForObject(url, LatLon.class);
    }
}
