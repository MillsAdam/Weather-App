using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Capstone.Models
{
    public class WeatherDBObject
    {
        public int Id { get; set; }
        public int UserId { get; set; }
        public string Zipcode { get; set; }
        public string Main { get; set; }
        public string Description { get; set; }
        public double Temperature { get; set; }
    }
}