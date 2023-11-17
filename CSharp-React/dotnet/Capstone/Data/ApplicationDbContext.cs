using Microsoft.EntityFrameworkCore;
using Capstone.Models;

namespace Capstone.Data
{
    public class ApplicationDbContext : DbContext
    {
        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options) : base(options) { }

        // Define DbSets for each of your entities
        public DbSet<User> Users { get; set; }
        // Add DbSets for other entities here (WeatherData, etc)

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            base.OnConfiguring(optionsBuilder);
        }

    }
}