package com.example;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.bouncycastle.util.encoders.Base64;

import com.example.dao.JdbcUserDao;
import com.example.dao.UserDao;
import com.example.models.LatLon;
import com.example.models.User;
import com.example.models.Weather;
// import com.example.models.WeatherObject;
import com.example.security.PasswordHasher;
import com.example.services.WeatherService;

public class App 
{

    private final UserDao userDao;
    private final Scanner userInput;
    private final PasswordHasher passwordHasher;
    private User loggedInUser;
    private WeatherService weatherService = new WeatherService();

    public static void main( String[] args )
    {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/weather_db");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");

        App app = new App(dataSource);
        app.run();

        // Scanner input = new Scanner(System.in);
        // System.out.print( "Enter zip-code: " );
        // String zipCode = input.nextLine();

        // WeatherService weatherService = new WeatherService();
        // LatLon latLon = weatherService.getLatLon(zipCode);

        // System.out.println(latLon);
        // System.out.println("You are in " + latLon.getName());
        // System.out.println("Your latitude is " + latLon.getLat());
        // System.out.println("Your longitude is " + latLon.getLon());
        // System.out.println();

        // WeatherObject weatherObject = weatherService.getWeather(latLon);

        // System.out.println(weatherObject);
        // System.out.println("The temperature is " + weatherObject.getMain().getTemp() + " degrees Fahrenheit");
        // System.out.println("The temperature feels like " + weatherObject.getMain().getFeelsLike() + " degrees Fahrenheit");
        // System.out.println("Today's weather: " + weatherObject.getWeather()[0].getDescription());
    }

    /** 
     * Set up the DAOs and scanner for the application. 
     * 
     * @param datasource the connection information to the SQL database
     */
    public App(DataSource datasource) {
        passwordHasher = new PasswordHasher();
        userDao = new JdbcUserDao(datasource);
        userInput = new Scanner(System.in);
    }

    /** 
     * Run the application. 
     */
    public void run() {
        printGreeting();

        while (true) {
            printMenu();
            String option = askPrompt();

            if ("a".equalsIgnoreCase(option)) {
                addNewUser();
            } else if ("s".equalsIgnoreCase(option)) {
                showUsers();
            } else if ("l".equalsIgnoreCase(option)) {
                boolean loggedIn = loginUser();
                if (loggedIn) {
                    showWeatherMenu();
                }
            } else if ("q".equalsIgnoreCase(option)) {
                System.out.println("Thanks for using the Weather App!");
                break;
            } else {
                System.out.println(option + " is not a valid option.  Please select again.");
            }
        }
    }

    public void showWeatherMenu() {
        int zipCode = 0;
        while (true) {
            printWeatherMenu();
            String option = askPrompt();

            if ("a".equalsIgnoreCase(option)) {
                zipCode = addZipCode();
            } else if ("s".equalsIgnoreCase(option)) {
                getWeather(zipCode);
            } else if ("q".equalsIgnoreCase(option)) {
                System.out.println("Thanks for using the Weather App!");
                break;
            } else {
                System.out.println(option + " is not a valid option.  Please select again.");
            }
        }
    }

    public void getWeather(int zipCode) {
        if (zipCode == 0) {
            System.out.println("Invalid zip code or zip code not entered!");
        } else {
            System.out.println("User is " + loggedInUser.getUserId() + " and zip is " + zipCode);
            LatLon latLon = weatherService.getLatLon(zipCode + "");
            Weather weather = weatherService.getWeather(latLon);
            System.out.println(weather);
        }
    }

    private void printWeatherMenu() {
        System.out.println("Logged in user Menu");
        System.out.println("============");
        System.out.println("(A)dd a zip code");
        System.out.println("(S)how weather");
        System.out.println("(Q)uit");
        System.out.println();
    }

    private int addZipCode() {
        boolean isValid = false;
        int zipCode = 0;

        while(!isValid) {
            System.out.print("Enter zip code: ");
            String zipCodeString = userInput.nextLine();
            try {
                zipCode = Integer.parseInt(zipCodeString);
                isValid = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid zip code! Please try again.");
            }
        }
        return zipCode;
    }

    /** 
     * Take a username and password from the user and check it against the DAO via the isUsernameAndPasswordValid() method. 
     * If the method returns false it means the username/password are invalid. 
     * You don't know what's specifically wrong about the login, just that the combined username & password are invalid.
     * You don't want to give an attacker any information about what they got right or wrong.
     * Information like that is gold to an attacker because then they know what they're getting right and what they're getting wrong.
     * 
     * changed output from void to boolean to return true if login successful, false otherwise
     */
    private boolean loginUser() {
        System.out.println("Login");
        System.out.println("=====");
        System.out.print("Enter username: ");
        String username = userInput.nextLine();
        System.out.print("Enter password: ");
        String password = userInput.nextLine();

        if (isUsernameAndPasswordValid(username, password)) {
            loggedInUser = new User();
            loggedInUser.setUsername(username);
            User user = userDao.getUserByUsername(username);
            loggedInUser.setUserId(user.getUserId());
            System.out.println("Welcome " + username + "!");
            System.out.println();
            return true; // Login successful
        } else {
            System.out.println("Invalid username/password combination, please try again.");
            System.out.println();
            return false; // Login unsuccessful
        }
    }

    /** 
     * Check the username and password are valid 
     * 
     * @param username the supplied username to validate
     * @param password the supplied password to validate
     * @return true if the username and password are valid, false otherwise
     */
     private boolean isUsernameAndPasswordValid(String username, String password) {
        Map<String, String> passwordAndSalt = userDao.getPasswordAndSaltByUsername(username);
        if (passwordAndSalt.isEmpty()) {
            return false;
        }
        String storedSalt = passwordAndSalt.get("salt");
        String storedPassword = passwordAndSalt.get("password");
        String hashedPassword = passwordHasher.computeHash(password, Base64.decode(storedSalt));
        return storedPassword.equals(hashedPassword);
     }

     /** 
      * Add a new user to the system.
      * Anyone can register a new account like this. 
      * It calls createUser() in the DAO to save it to the database.
      */
      private void addNewUser() {
        System.out.println("Enter the following information to register a new user: ");
        System.out.print("Username: ");
        System.out.flush();
        String username = userInput.nextLine();
        System.out.print("Password: ");
        System.out.flush();
        String password = userInput.nextLine();

        // generate hashed password and salt
        byte[] salt = passwordHasher.generateRandomSalt();
        String hashedPassword = passwordHasher.computeHash(password, salt);
        String saltString = new String(Base64.encode(salt));

        User user = userDao.createUser(username, hashedPassword, saltString);
        System.out.println("Successfully created user " + user.getUsername() + " with id " + user.getUserId() + "!");
        System.out.println();
      }

      /** 
       * Show all the users that are in the data store. 
       * You can't show passwords because you don't have them!
       * Passwords in the data store are hashed and you can see that by opening up a SQL client like pgAdmin or DbVisualizer and looking at the users table.
       * 
       * Only allow access to this to logged-in users. 
       * If a user isn't logged in, give them a message and leave. 
       * Having an 'if' statement like this at the top of the method is a common way of handling authorization checks.
       */
      private void showUsers() {
        if (loggedInUser == null) {
            System.out.println("You must be logged in to do that!");
            System.out.println("Press enter to continue...");
            System.out.flush();
            userInput.nextLine();
            return;
        }

        List<User> users = userDao.getUsers();
        System.out.println("List of Users");
        System.out.println("=============");
        for (User user : users) {
            System.out.println(user.getUserId() + ": " + user.getUsername());
        }
        System.out.println();
        System.out.println("Press enter to continue...");
        System.out.flush();
        userInput.nextLine();
        System.out.println();
      }

      private void printMenu() {
        System.out.println("(A)dd a new User");
        System.out.println("(S)how all Users");
        System.out.println("(L)ogin");
        System.out.println("(Q)uit");
        System.out.println();
      }

      private String askPrompt() {
        String name;
        if (loggedInUser == null) {
            name = "Unauthenticated User";
        } else {
            name = loggedInUser.getUsername();
        }

        System.out.println("Welcome " + name + "!");
        System.out.println("What would you like to do?");
        System.out.flush();
        String selection;
        try {
            selection = userInput.nextLine();
        } catch (Exception e) {
            selection = "*";
        }
        return selection;
      }

      private void printGreeting() {
        System.out.println("Welcome to the Weather App!");
        System.out.println("===========================");
        System.out.println();
      }
}

