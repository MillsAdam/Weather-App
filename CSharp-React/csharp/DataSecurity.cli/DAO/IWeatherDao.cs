using System;
using System.Collections.Generic;
using System.Text;
using System.Linq;
using System.Threading.Tasks;
using WeatherDB.Cli.Models;

namespace WeatherDB.Cli.DAO
{
    public interface IWeatherDao
    {
        WeatherObject CreateWeather(int userId, WeatherObject weatherObject, int zipCode);
        WeatherObject GetWeatherByUserId(int userId);
    }
}