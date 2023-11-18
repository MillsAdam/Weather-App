import React from 'react';
import WeatherComponent from '../components/WeatherComponent';
import '../styles/Weather.css';

function Weather() {
    return (
        <div className="weather">
            <h1>Weather Page</h1>
            <WeatherComponent />
        </div>
    );

}

export default Weather;