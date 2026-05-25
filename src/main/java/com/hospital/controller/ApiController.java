package com.hospital.controller;

import com.hospital.DatabaseConnection;
import com.hospital.model.User;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ApiController {

    @GetMapping("/")
    public String home() {
        return "Hospital Management API is Live!";
    }

    @GetMapping("/api/test")
    public String testApi() {
        return "API Connected Successfully";
    }

    @GetMapping("/api/users")
    public List<User> getUsers() {

        List<User> users = new ArrayList<>();

        try {
            Connection conn = DatabaseConnection.getConnection();

            String sql = "SELECT * FROM users";
            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                users.add(
                        new User(
                                rs.getInt("id"),
                                rs.getString("username"),
                                rs.getString("password")
                        )
                );
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    @PostMapping("/api/users")
    public String addUser(@RequestBody User user) {

        try {
            Connection conn = DatabaseConnection.getConnection();

            String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, user.getUsername());
            pst.setString(2, user.getPassword());

            pst.executeUpdate();

            conn.close();

            return "User Added Successfully";

        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to Add User";
        }
    }
}