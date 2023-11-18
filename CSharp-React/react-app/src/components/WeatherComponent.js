import React, { useState} from 'react';
import WeatherService from '../services/WeatherService';

function WeatherComponent() {
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

    async function getWeather(e) {
        e.preventDefault();
        try {
            const data = await WeatherService.getWeather(zipcode);;
            setWeather({
                lat: data.coord.lat,
                lon: data.coord.lon,
                name: data.name,
                temp: data.main.temp,
                feelsLike: data.main.feels_like,
                description: data.weather[0].description,
                image: `https://openweathermap.org/img/wn/${data.weather[0].icon}@2x.png`,
            });
        } catch (error) {
            console.error('An error occurred: ', error);
        }
    }

    return (
        <div>
            <form onSubmit={getWeather}>
                <label>Enter Zipcode: </label>
                <input className="form" type="text" value={zipcode} onChange={(e) => setZipcode(e.target.value)} />
                <input type="submit" className="form" value="Get Weather" />
            </form>
            <div className="output">
                {weather.lat && (
                    <div>
                        You are in {weather.name}.  Your latitude is {weather.lat} and your longitude is {weather.lon}.
                    </div>
                )}
                {weather.temp && (
                    <div>
                        Today's temp is {weather.temp} and it feels like {weather.feelsLike}. {weather.description}
                        <div>
                            <img src={weather.image} alt="Weather icon" />
                        </div>
                    </div>
                )}
            </div>

        </div>
    );
}

export default WeatherComponent;