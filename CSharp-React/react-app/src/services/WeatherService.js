import axios from 'axios';

const WeatherService = {
    getWeather(zipcode) {
        return axios.get(`http://localhost:5000/weather?zipcode=${zipcode}`);
    },

    createWeather(zipcode) {
        return axios.post(`http://localhost:5000/weather/create?zipcode=${zipcode}`);
    },
};

export default WeatherService;