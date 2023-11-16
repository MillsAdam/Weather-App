using csharp.Models;
using csharp.Services;

public class Program 
{
    private static void Main(string[] args) 
    {
        WeatherService weatherService = new WeatherService();

        Console.Write("Enter Zip Code: ");
        string zipCode = Console.ReadLine();
        if (zipCode == null) {
            Console.WriteLine("Zip Code cannot be null.");
            return;
        }

        LatLon latLon = weatherService.GetLatLon(zipCode);

        Console.WriteLine($"You are in {latLon.Name}");
        Console.WriteLine($"Your latitude is {latLon.Lat}");
        Console.WriteLine($"Your longitude is {latLon.Lon}");
        System.Console.WriteLine();

        WeatherObject weatherObject = weatherService.GetWeather(latLon);

        System.Console.WriteLine($"The weather is {weatherObject.weather[0].Description}.");
        System.Console.WriteLine($"The temperature is {weatherObject.main.Temp} degrees Fahrenheit.");
        System.Console.WriteLine($"The feels like temperature is {weatherObject.main.Feels_like} degrees Fahrenheit.");
        System.Console.WriteLine($"The humidity is {weatherObject.main.Humidity}%.");
        System.Console.WriteLine($"The wind speed is {weatherObject.wind.Speed} mph.");
        System.Console.WriteLine();
    }
}
