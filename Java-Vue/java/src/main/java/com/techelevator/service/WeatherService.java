package com.techelevator.service;


import com.techelevator.model.WeatherObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.techelevator.model.LatLon;

@Component
public class WeatherService {
    private RestTemplate template = new RestTemplate();
    @Value("${API_URL}")
    private String API_URL;
    @Value("${API_KEY}")
    private String API_KEY;


    public LatLon getLatLon(String zipcode){
        String url = API_URL + "geo/1.0/zip?zip=" + zipcode + "&appid=" + API_KEY;
        return template.getForObject(url, LatLon.class);
    }

    public WeatherObject getWeather(LatLon latLon){
        String url = "https://api.openweathermap.org/data/2.5/weather?lat=" +
                latLon.getLat() + "&lon=" +
                latLon.getLon() + "&units=imperial&appid=" + API_KEY;

        return template.getForObject(url, WeatherObject.class);
    }

}
