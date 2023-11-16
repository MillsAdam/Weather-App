using System.ComponentModel.Design;
using System.Net;
using System.ComponentModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using RestSharp;
using csharp.Models;
using dotenv.net;


namespace csharp.Services
{
    public class WeatherService
    {
        private readonly string API_KEY;
        private const string WEATHER_API_URL = "https://api.openweathermap.org/";
        private static RestClient? restClient; // added ? to declare as nullable

        public WeatherService()
        {
            DotEnv.Load(); // Load the .env file
            API_KEY = Environment.GetEnvironmentVariable("OPEN_WEATHER_API_KEY") ?? "default_api_key";
            if (restClient == null) {
                restClient = new RestClient(WEATHER_API_URL);
            }
        }

        public LatLon GetLatLon(string zipCode)
        {

            // RestRequest restRequest = new RestRequest(WEATHER_API_URL + "geo/1.0/zip?zip=" + zipCode + "&units=imperial&appid=" + API_KEY);
            RestRequest restRequest = new RestRequest($"{WEATHER_API_URL}geo/1.0/zip?zip={zipCode}&appid={API_KEY}");
            IRestResponse<LatLon> response = restClient.Get<LatLon>(restRequest);
            CheckForError(response);
            return response.Data;
            
        }

        public WeatherObject GetWeather(LatLon latLon)
        {
            RestRequest restRequest = new RestRequest($"{WEATHER_API_URL}data/2.5/weather?lat={latLon.Lat}&lon={latLon.Lon}&units=imperial&appid={API_KEY}");
            IRestResponse<WeatherObject> response = restClient.Get<WeatherObject>(restRequest);
            CheckForError(response);
            return response.Data;
        }

        private void CheckForError(IRestResponse response)
        {
            if (!response.IsSuccessful) {
                Console.WriteLine($"Error: {response.StatusCode}");
                Console.WriteLine($"Content: {response.Content}");
                throw new HttpRequestException($"There was an error in the call to the server.");
            }
        }
    }
}