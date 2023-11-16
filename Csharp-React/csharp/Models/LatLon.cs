using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Newtonsoft.Json;

namespace csharp.Models
{
    /// <summary>
    ///   This class represents the data returned from the OpenWeatherMap API.
    ///   It is used to deserialize the JSON response into a C# object.
    ///   Represents the latitude and longitude of a location along with its name.
    /// </summary>
    /// 
    public class LatLon
    {
        [JsonProperty("lat")]
        public double Lat { get; set; }

        [JsonProperty("lon")]
        public double Lon { get; set; }

        [JsonProperty("name")]
        public string? Name { get; set; } // added ? to make this nullable

    }
}