import axios from 'axios';

const WeatherService = {
    getWeather(zipcode) {
        return axios.get(`http://localhost:5000/weather?zipcode=${zipcode}`)
            .then(response => response.data)
            .catch(error => {
                console.error('An error occurred: ', error);
                throw error;
            });
    },

    createWeather(zipcode, authToken) {
        return axios.post(`http://localhost:5000/weather/create?zipcode=${zipcode}`, {}, {
            headers: {
                Authorization: `Bearer ${authToken}`
            },
        })
            .then(response => response.data)
            .catch(error => {
                console.error('An error occurred: ', error);
                throw error;
            });
    },
};

export default WeatherService;