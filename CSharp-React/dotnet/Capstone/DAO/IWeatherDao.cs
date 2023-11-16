﻿using System;
using System.Collections.Generic;
using System.Text;
using weather_cli_csharp.Models;
using Capstone.Models;

namespace WeatherDb.Cli.DAO
{
    public interface IWeatherDao
    {
        WeatherObject createWeather(int zipcode, WeatherObject weather, LatLon latlon, User user);

        WeatherObject getWeatherByUser(User user);

    }
}
