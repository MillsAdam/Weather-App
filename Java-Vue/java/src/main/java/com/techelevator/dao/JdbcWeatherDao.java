package com.techelevator.dao;

import com.techelevator.model.LatLon;
import com.techelevator.model.User;
import com.techelevator.model.Weather;
import com.techelevator.model.WeatherDBObject;
import com.techelevator.model.WeatherObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Component
public class JdbcWeatherDao implements WeatherDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcWeatherDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public WeatherDBObject createWeather(WeatherObject weather, LatLon latLon, User user) {
        WeatherDBObject weatherDBObject = new WeatherDBObject();
        String sql = "INSERT INTO weather (user_id, zipcode, main, description, temperature) " +
                "VALUES(?, ?, ?, ?, ?) RETURNING id;";
        int weatherId = jdbcTemplate.queryForObject(sql, new Object[] {user.getId(), latLon.getZip(),  weather.getWeather()[0].getMain(),
                weather.getWeather()[0].getDescription(),
                weather.getMain().getTemp()}, Integer.class);
        weatherDBObject.setId(weatherId);
        weatherDBObject.setUserId(user.getId());
        weatherDBObject.setZipcode(latLon.getZip());
        weatherDBObject.setMain(weather.getWeather()[0].getMain());
        weatherDBObject.setDescription(weather.getWeather()[0].getDescription());
        weatherDBObject.setTemperature(weather.getMain().getTemp());
        return weatherDBObject;
    }

    @Override
    public Weather getWeatherByUser(User user) {
        return null;
    }
}
