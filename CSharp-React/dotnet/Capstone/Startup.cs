﻿using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using System.Text;
using System.IdentityModel.Tokens.Jwt;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.IdentityModel.Tokens;
using Capstone.DAO;
using Capstone.Security;
using weather_cli_csharp.Services;
using Microsoft.EntityFrameworkCore;
using Capstone.Data;
using WeatherDb.Cli.DAO;

namespace Capstone
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            services.AddControllers();

            // services.AddCors(options =>
            // {
            //     options.AddDefaultPolicy(
            //         builder =>
            //         {
            //             builder.AllowAnyOrigin().AllowAnyHeader().AllowAnyMethod().AllowCredentials();
            //         });
            // });

            services.AddCors(options =>
            {
                options.AddDefaultPolicy(
                    builder =>
                    {
                        builder.WithOrigins("http://localhost:3000").AllowAnyHeader().AllowAnyMethod().AllowCredentials();
                    });
            });

            string connectionString = Configuration.GetConnectionString("DefaultConnection");

            services.AddDbContext<ApplicationDbContext>(options => 
                options.UseNpgsql(connectionString));

            // Register your WeatherService here
            services.AddTransient<WeatherService>();

            // Add IWeatherDao service registration here
            services.AddTransient<IWeatherDao, WeatherSqlDao>(m => new WeatherSqlDao(connectionString));

            // configure jwt authentication
            var key = Encoding.ASCII.GetBytes(Configuration["JwtSecret"]);
            JwtSecurityTokenHandler.DefaultInboundClaimTypeMap[JwtRegisteredClaimNames.Sub] = "sub";
            services.AddAuthentication(x =>
            {
                x.DefaultAuthenticateScheme = JwtBearerDefaults.AuthenticationScheme;
                x.DefaultChallengeScheme = JwtBearerDefaults.AuthenticationScheme;
            })
            .AddJwtBearer(x =>
            {
                x.RequireHttpsMetadata = false;
                x.SaveToken = true;
                x.TokenValidationParameters = new TokenValidationParameters
                {
                    ValidateIssuerSigningKey = true,
                    IssuerSigningKey = new SymmetricSecurityKey(key),
                    ValidateIssuer = false,
                    ValidateAudience = false,
                    NameClaimType = "name"
                };
            });

            // Dependency Injection configuration
            services.AddSingleton<ITokenGenerator>(tk => new JwtGenerator(Configuration["JwtSecret"]));
            services.AddSingleton<IPasswordHasher>(ph => new PasswordHasher());
            services.AddTransient<IUserDao>(m => new UserSqlDao(connectionString));
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }

            // OLD CODE ORIGINAL
            // app.UseAuthentication();
            // app.UseHttpsRedirection();
            // app.UseRouting();
            // app.UseCors();
            // app.UseAuthorization();

            // NEW CODE REFORMATTED
            // Use Cors must be placed before UseRouting
            app.UseCors();

            // UseHttpsRedirection must be placed after UseCors
            // app.UseHttpsRedirection();

            app.UseRouting();
            app.UseAuthentication(); // Authentication should be before Authorization
            app.UseAuthorization();
            // NEW CODE REFORMATTED

            app.UseEndpoints(endpoints =>
            {
                endpoints.MapControllers();
            });
        }
    }
}
