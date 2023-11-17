

using Capstone.DAO;
using Capstone.Models;
using Microsoft.AspNetCore.Mvc;
using weather_cli_csharp.Models;
using weather_cli_csharp.Services;
using WeatherDb.Cli.DAO;

namespace Capstone.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class WeatherController : ControllerBase
    {
        private readonly WeatherService _weatherService;
        private readonly IWeatherDao _weatherDao;
        private readonly IUserDao _userDao;

        public WeatherController(WeatherService weatherService, IWeatherDao weatherDao, IUserDao userDao)
        {
            _weatherService = weatherService;
            _weatherDao = weatherDao;
            _userDao = userDao;
        }

        [HttpGet]
        public ActionResult<WeatherObject> GetWeather([FromQuery] string zipcode) 
        {
            LatLon latLon = _weatherService.GetLatLon(zipcode);
            WeatherObject weather = _weatherService.getWeather(latLon);
            return weather;
        }

        [HttpPost("create")]
        public ActionResult<WeatherDBObject> CreateWeather([FromQuery] string zipcode)
        {
            string username = User.Identity.Name;
            User user = _userDao.GetUser(username);
            
            LatLon latLon = _weatherService.GetLatLon(zipcode);
            WeatherObject weather = _weatherService.getWeather(latLon);
            return _weatherDao.createWeather(zipcode, weather, latLon, user);
        }
    }
}