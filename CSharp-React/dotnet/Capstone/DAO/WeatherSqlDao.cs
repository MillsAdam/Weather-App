using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Text;
using weather_cli_csharp.Models;
using Capstone.Models;
using Npgsql;

namespace WeatherDb.Cli.DAO
{
    public class WeatherSqlDao : IWeatherDao
    {
        private readonly string connectionString;

        public WeatherSqlDao(string connectionString)
        {
            this.connectionString = connectionString;
        }

        public WeatherDBObject createWeather(string zipcode, WeatherObject weather, LatLon latlon, User user)
        {
            using (NpgsqlConnection connection = new NpgsqlConnection(connectionString))
            {
                connection.Open();

                NpgsqlCommand command = connection.CreateCommand();
                command.CommandText = @"INSERT INTO weather (user_id, zipcode, main, description, temperature)
                                        VALUES (@userId, @zipcode, @main, @description, @temperature);";

                command.Parameters.AddWithValue("@userId", user.UserId);
                command.Parameters.AddWithValue("@zipcode",zipcode);
                command.Parameters.AddWithValue("@main", weather.weather[0].main);
                command.Parameters.AddWithValue("@description", weather.weather[0].description);
                command.Parameters.AddWithValue("@temperature", weather.main.temp);

                command.ExecuteNonQuery();

                return null;
            }
        }

        public WeatherObject getWeatherByUser(User user)
        {
            throw new NotImplementedException();
        }
    }
}
