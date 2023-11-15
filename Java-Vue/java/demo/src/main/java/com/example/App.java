package com.example;

import java.util.Scanner;

import com.example.models.LatLon;
import com.example.models.WeatherObject;
import com.example.services.WeatherService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Scanner input = new Scanner(System.in);
        System.out.print( "Enter zip-code: " );
        String zipCode = input.nextLine();

        WeatherService weatherService = new WeatherService();
        LatLon latLon = weatherService.getLatLon(zipCode);

        System.out.println(latLon);
        System.out.println("You are in " + latLon.getName());
        System.out.println("Your latitude is " + latLon.getLat());
        System.out.println("Your longitude is " + latLon.getLon());
        System.out.println();

        WeatherObject weatherObject = weatherService.getWeather(latLon);

        System.out.println(weatherObject);
        System.out.println("The temperature is " + weatherObject.getMain().getTemp() + " degrees Fahrenheit");
        System.out.println("The temperature feels like " + weatherObject.getMain().getFeelsLike() + " degrees Fahrenheit");
        System.out.println("Today's weather: " + weatherObject.getWeather()[0].getDescription());
    }
}

