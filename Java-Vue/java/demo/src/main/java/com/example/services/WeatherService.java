package com.example.services;

import org.springframework.web.client.RestTemplate;

public class WeatherService {
    private RestTemplate restTemplate = new RestTemplate();
    private static final String API_URL = "http://api.openweathermap.org/geo/1.0/zip";
    private static final String API_KEY = "896d7ca05ae3d29c18cd4b2c9ed6a815";
}
