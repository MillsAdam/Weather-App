

using Microsoft.AspNetCore.Mvc;
using weather_cli_csharp.Models;
using weather_cli_csharp.Services;

namespace Capstone.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class WeatherController : ControllerBase
    {
        private readonly WeatherService _weatherService;
        public WeatherController(WeatherService weatherService)
        {
            _weatherService = weatherService;
        }

        [HttpGet]
        public ActionResult<WeatherObject> GetWeather([FromQuery] string zipcode) 
        {
            LatLon latLon = _weatherService.GetLatLon(zipcode);
            WeatherObject weather = _weatherService.getWeather(latLon);
            return weather;
        }
    }
}