import axios from 'axios';

export default {
    getWeather(zipcode){
        return axios.get(`/weather?zipcode=${zipcode}`);
    },

    createWeather(zipcode) {
        return axios.post(`/createWeather?zipcode=${zipcode}`);
    }
}