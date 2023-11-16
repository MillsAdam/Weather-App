using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Threading.Tasks;
using WeatherDB.Cli.Models;
using System.Reflection.Emit;
using System.Text;
using WeatherDB.Cli;
using static WeatherDB.Cli.Models.WeatherObject;

namespace WeatherDB.Cli.DAO
{
    public class WeatherSqlDao : IWeatherDao
    {
        private readonly string connectionString;

        public WeatherSqlDao(string connString)
        {
            connectionString = connString;
        }

        public WeatherObject CreateWeather(int userId, WeatherObject weatherObject, int zipCode)
        {
            using (SqlConnection connection = new SqlConnection(connectionString))
            {
                connection.Open();

                SqlCommand command = connection.CreateCommand();
                command.CommandText = @"INSERT INTO weather (user_id, zip_code, main, description, temperature, feels_like) VALUES (@user_id, @zip_code, @main, @description, @temperature, @feels_like);";
                command.Parameters.AddWithValue("@userId", userId);
                command.Parameters.AddWithValue("@zip_code", zipCode);
                command.Parameters.AddWithValue("@main", weatherObject.weather[0].Main);
                command.Parameters.AddWithValue("@description", weatherObject.weather[0].Description);
                command.Parameters.AddWithValue("@temperature", weatherObject.main.Temp);
                command.Parameters.AddWithValue("@feels_like", weatherObject.main.Feels_like);

                command.ExecuteNonQuery();
            }

            return GetWeatherByUserId(userId);
        }

        public WeatherObject GetWeatherByUserId(int userId) {
            WeatherObject weatherObject = null;
            using (SqlConnection connection = new SqlConnection(connectionString)) {
                connection.Open();

                SqlCommand command = connection.CreateCommand();
                command.CommandText = "SELECT user_id, main, description, temperature, feels_like FROM weather WHERE user_id = @user_id;";
                command.Parameters.AddWithValue("@user_id", userId);

                SqlDataReader reader = command.ExecuteReader();
                if (reader.Read()) {
                    weatherObject = MapRowToWeatherObject(reader);
                }
            }

            return weatherObject;
        }

        public WeatherObject MapRowToWeatherObject(SqlDataReader reader) {
            WeatherObject Feels_like = new WeatherObject();
            Feels_like.main = new Main();
            Weather weather = new Weather();
            Feels_like.weather = new List<Weather>();
            double feels_like = Convert.ToDouble(reader["feels_like"]);

            WeatherObject Temp = new WeatherObject();
            Temp.main = new Main();
            // Weather weather = new Weather();
            Temp.weather = new List<Weather>();
            double temperature = Convert.ToDouble(reader["temperature"]);

            string main = Convert.ToString(reader["main"]);
            weather.Main = main;
            
            string description = Convert.ToString(reader["description"]);
            weather.Description = description;

            Temp.main.Temp = temperature;
            Feels_like.main.Feels_like = feels_like;
            Temp.weather.Add(weather);
            Feels_like.weather.Add(weather);
            return Feels_like;
        }
    }
}