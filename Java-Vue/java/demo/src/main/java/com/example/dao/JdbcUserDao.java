package com.example.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.example.models.User;

public class JdbcUserDao implements UserDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User createUser(String username, String hashedPassword, String saltString) {
        String sql = "INSERT INTO users (username, password, salt) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, username, hashedPassword, saltString);
        User newUser = getUserByUsername(username);
        return newUser;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = null;
        String sqlSearchForUser = "SELECT id, username FROM users WHERE UPPER(username) = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSearchForUser, username.toUpperCase());
        if (results.next()) {
            user = mapRowToUser(results);
        }
        return user;
    }

    @Override
    public Map<String, String> getPasswordAndSaltByUsername(String username) {
        Map<String, String> passwordAndSalt = new HashMap<>();
        String sqlSearchForUser = "SELECT sale, password FROM users WHERE UPPER(username) = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSearchForUser, username.toUpperCase());
        if (results.next()) {
            passwordAndSalt.put("salt", results.getString("salt"));
            passwordAndSalt.put("password", results.getString("password"));
        }
        return passwordAndSalt;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        String sqlSelectAllUsers = "SELECT id, username FROM users;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectAllUsers);
        while (results.next()) {
            User user = mapRowToUser(results);
            users.add(user);
        }
        return users;
    }

    private User mapRowToUser(SqlRowSet results) {
        User user = new User();
        user.setUserId(results.getInt("id"));
        user.setUsername(results.getString("username"));
        return user;
    }
    
}
