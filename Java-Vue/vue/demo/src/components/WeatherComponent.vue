<template>
    <div>
        <form v-on:submit.prevent="getWeather">
            <label for="zip-code">Enter Zip Code:</label>
            <input type="text" id="zip-code" v-model="zipCode" />
            <input type="submit" value="Get Weather" />
        </form>
        
        <div v-show="lat != ''">
            You are in {{ name }}.
            You're latitude is {{ lat }} and your longitude is {{ lon }}.
        </div>

        <div v-show="temp != ''">
            Today's temp is {{ temp }} and it feels like {{ feelsLike }}.
            The weather is {{ description }}.
            <div>
                <img :src="image" />
            </div>
        </div>
    </div>
</template>

<script>
import WeatherService from '../services/WeatherService';

export default {
    data() {
        return {
            zipCode: '',
            lat: '',
            lon: '',
            name: '',
            temp: '',
            feelsLike: '',
            description: '',
            id: '',
            icon: '',
            image: '',
        };
    },

    methods: {
        async getWeather() {
            try {
                const locationResponse = await WeatherService.getLatLon(this.zipCode);
                console.log(locationResponse);
                this.lat = locationResponse.data.lat;
                this.lon = locationResponse.data.lon;
                this.name = locationResponse.data.name;

                const weatherResponse = await WeatherService.getWeather(this.lat, this.lon);
                console.log(weatherResponse);
                this.temp = weatherResponse.data.main.temp;
                this.feelsLike = weatherResponse.data.main.feels_like;
                this.description = weatherResponse.data.weather[0].description;
                this.icon = weatherResponse.data.weather[0].icon;
                this.image = `http://openweathermap.org/img/w/${this.icon}.png`;
                // Process weather data
            } catch (error) {
                console.error(error);
                // Handle error appropriately
            }
        },
    },
}
</script>


<style scoped>

</style>