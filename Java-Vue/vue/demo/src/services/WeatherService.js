import axios from 'axios'

const http = axios.create({
    baseURL: 'http://api.openweathermap.org/',
});

const API_KEY = process.env.VUE_APP_OPEN_WEATHER_API_KEY;

export default {
    getLatLon(zipCode) {
        return http.get(`geo/1.0/zip?zip=${zipCode}&appid=${API_KEY}`)
    },

    getWeather(lat, lon) {
        return http.get(`data/2.5/weather?lat=${lat}&lon=${lon}&units=imperial&appid=${API_KEY}`)
    }
}