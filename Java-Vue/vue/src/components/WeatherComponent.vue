<template>
  <div>
    <form v-on:submit.prevent="getWeather">
      <label>Enter Zipcode: </label>
      <input class="form" type="text" v-model="zipcode" />
      <input type="submit" class="form" value="Get Weather" />
    </form>
    <div class="output">
      <div v-show="lat != ''">
        You are in {{ name }} Your latitude is {{ lat }} and longitude is
        {{ lon }}
      </div>
      <div v-show="temp != ''">
        Today's temp is {{ temp }} and it feel like {{ feelsLike }}

        {{ description }}
        <div>
        <img :src="image" alt="" />
      </div>
      </div>
    </div>
  </div>
</template>
  
  <script>
import service from "../services/WeatherService.js";
export default {
  data() {
    return {
      zipcode: "",
      lat: "",
      lon: "",
      name: "",
      temp: "",
      feelsLike: "",
      description: "",
      id: "",
      icon: "",
      image: "",
    };
  },
  methods: {
    async getWeather() {
      try {
        const response = await service.getWeather(this.zipcode);
        console.log(response.data);
        this.temp = response.data.main.temp;
        this.feelsLike = response.data.main.feels_like;
        this.description = response.data.weather[0].description;
        this.icon = response.data.weather[0].icon;
        this.image =
          "https://openweathermap.org/img/wn/" + this.icon + "@2x.png";
        this.id = response.data.id;
        const createResponse = await service.createWeather(this.zipcode);
        console.log(createResponse.data);
      } catch (error) {
        console.error('An error occured:', error.response || error.message || error);
      }
    },
  },
};
</script>
  
  <style scoped>
output, form{
  display: block;
  margin: 20px;
}
img {
  margin: 20px;
  border: 2px solid black;
  background-color: lightgray;
}
</style>