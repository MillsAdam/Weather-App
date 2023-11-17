using RestSharp;
using System.Net.Http;
using System.IO.Compression;
using System.Text.Json.Serialization;
using Newtonsoft.Json;
using weather_cli_csharp.Models;

namespace weather_cli_csharp.Services
{
    public class WeatherService
    {
        protected static RestClient client = null;
        private string API_URL = "http://api.openweathermap.org/";
        private string API_KEY = "896d7ca05ae3d29c18cd4b2c9ed6a815";

        public WeatherService()
        {
            if (client == null)
            {
                client = new RestClient(API_URL);
            }
        }

        public LatLon GetLatLon(string zip)
        {
            RestRequest request = new RestRequest(API_URL + "geo/1.0/zip?zip=" + zip +
                "&appid="+ API_KEY);
            IRestResponse<LatLon> response = client.Get<LatLon>(request);
            CheckForError(response);
            return response.Data;
        }

        public WeatherObject getWeather(LatLon latLon)
        {
            string url = API_URL + "data/2.5/weather?lat=" + latLon.Lat +
                "&lon=" + latLon.Lon + "&units=imperial&appid=" + API_KEY;
            RestRequest request = new RestRequest(url);
            IRestResponse<WeatherObject> response = client.Get<WeatherObject>(request);
            CheckForError(response);
     
           
            return response.Data;
        }

        private void CheckForError(IRestResponse response)
        {
            if (!response.IsSuccessful)
            {
                // TODO: Write a log message for future reference

                throw new HttpRequestException($"There was an error in the call to the server");
            }
        }


    }
}
