package com.example.services;

import org.springframework.web.client.RestTemplate;
import com.example.models.LatLon;
import com.example.models.Weather;
import com.example.models.WeatherObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    public Weather getWeather(LatLon latLon) {
        String url = API_URL + "data/2.5/weather?lat=" + latLon.getLat() + "&lon=" + latLon.getLon() + "&units=imperial&appid=" + API_KEY;
        String response = restTemplate.getForObject(url, String.class);

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode root = objectMapper.readTree(response);
            JsonNode weatherNode = root.path("weather");
            String main = weatherNode.path(0).path("main").asText();
            String description = weatherNode.path(0).path("description").asText();
            double temp = root.path("main").path("temp").asDouble();
            double feelsLike = root.path("main").path("feels_like").asDouble();
            return new Weather(main, description, temp, feelsLike);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    // public WeatherObject getWeather(LatLon latLon) {
    //     String url = "https://api.openweathermap.org/data/2.5/weather?lat=" + latLon.getLat() + "&lon=" + latLon.getLon() + "&units=imperial&appid=" + API_KEY;
    //     return restTemplate.getForObject(url, WeatherObject.class);
    // }
}
