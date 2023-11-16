package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import com.techelevator.dao.WeatherDao;
import com.techelevator.model.LatLon;
import com.techelevator.model.User;
import com.techelevator.model.WeatherDBObject;
import com.techelevator.model.WeatherObject;
import com.techelevator.service.WeatherService;

import java.security.Principal;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class WeatherController {

    private WeatherService weatherService;
    private WeatherDao weatherDao;
    private UserDao userDao;

    public WeatherController(WeatherService weatherService, WeatherDao weatherDao, UserDao userDao) {
        this.weatherService = weatherService;
        this.weatherDao = weatherDao;
        this.userDao = userDao;
    }

    @GetMapping("/weather")
    public WeatherObject getWeather(@RequestParam String zipcode, Principal principal){
//        System.out.println("zip: " + zipcode);
        LatLon latLon = weatherService.getLatLon(zipcode);
        WeatherObject weather = weatherService.getWeather(latLon);

        return weather;
    }

    @PostMapping("/createWeather")
    public WeatherDBObject createWeather(@RequestParam String zipcode, Principal principal){
        LatLon latLon = weatherService.getLatLon(zipcode);
        WeatherObject weather = weatherService.getWeather(latLon);
        User user = userDao.getUserFromPrincipal(principal);
        return weatherDao.createWeather(weather, latLon, user);
    }
}
