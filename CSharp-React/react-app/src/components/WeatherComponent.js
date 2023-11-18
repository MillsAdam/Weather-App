import React, { useState } from 'react';
import WeatherService from '../services/WeatherService';
import { useAuth } from '../context/AuthContext';
import '../styles/Weather.css';

function WeatherComponent() {
    const { authToken } = useAuth();
    const [zipcode, setZipcode] = useState('');
    const [weather, setWeather] = useState({
        lat: '',
        lon: '',
        name: '',
        temp: '',
        feelsLike: '',
        description: '',
        image: '',
    });
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState(null);

    async function getWeather(e) {
        e.preventDefault();
        setIsLoading(true);
        setError(null);
        try {
            const data = await WeatherService.getWeather(zipcode);
            setWeather({
                lat: data.coord.lat,
                lon: data.coord.lon,
                name: data.name,
                temp: data.main.temp,
                feelsLike: data.main.feels_like,
                description: data.weather[0].description,
                image: `https://openweathermap.org/img/wn/${data.weather[0].icon}@2x.png`,
            });
            const createResponse = await WeatherService.createWeather(zipcode, authToken);
            console.log(createResponse);
        } catch (error) {
            console.error('An error occurred: ', error);
            setError('Failed to fetch weather data');
        }
        setIsLoading(false);
    }

    return (
        <div>
            <form onSubmit={getWeather}>
                <div className="form-input-group">
                    <label>Enter Zipcode: </label>
                    <input className="form" type="text" value={zipcode} onChange={(e) => setZipcode(e.target.value)} />
                    <input type="submit" className="form" value="Get Weather" />
                </div>
            </form>
            {isLoading && <div>Loading...</div>}
            {error && <div>{error}</div>}
            {weather && weather.name && <div className="output">
                <p>City: {weather.name}</p> 
                <p>Latitude: {weather.lat}</p>
                <p>Longitude: {weather.lon}.</p>
                <p>Today's temp: {weather.temp}</p>
                <p>Feels like: {weather.feelsLike}</p>
                <p>{weather.description}</p>
                <img src={weather.image} alt="Weather icon" className="weather-icon"/>
            </div>}
        </div>
    );
}

export default WeatherComponent;