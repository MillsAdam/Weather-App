package com.example.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.example.models.LatLon;
import com.example.models.User;
import com.example.models.Weather;

public class JdbcWeatherDao implements WeatherDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcWeatherDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Weather createWeather(Weather weather, User user, LatLon latLon) {
        String sql = "INSERT INTO weather (user_id, zip_code, main, description, temperature, feels_like) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getUserId(), latLon.getZipCode(), weather.getMain(), weather.getDescription(), weather.getTemp(), weather.getFeelsLike());
        return getWeatherByUser(user);
    }

    @Override
    public Weather getWeatherByUser(User user) {
        String sql = "SELECT main, description, temperature, feels_like FROM weather WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, user.getUserId());
        if (results.next()) {
            return mapRowToWeather(results);
        }
        return null;
    }

    @Override
    public Weather getWeatherByLatLon(LatLon latLon) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWeatherByLatLon'");
    }

    @Override
    public Weather getWeatherByLatLonAndUser(LatLon latLon, User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWeatherByLatLonAndUser'");
    }

    @Override
    public Weather getWeatherByWeatherId(int weatherId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWeatherByWeatherId'");
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

    private Weather mapRowToWeather(SqlRowSet row) {
        Weather weather = new Weather();
        // User user = new User();
        // LatLon latLon = new LatLon();
        // user.setUserId(row.getInt("user_id"));
        // latLon.setZipCode(row.getInt("zip_code"));
        weather.setMain(row.getString("main"));
        weather.setDescription(row.getString("description"));
        weather.setTemp(row.getDouble("temperature"));
        weather.setFeelsLike(row.getDouble("feels_like"));
        return weather;
    }
  
}
