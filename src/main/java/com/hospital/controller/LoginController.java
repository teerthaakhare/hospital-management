package com.hospital.controller;

import com.hospital.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    @FXML
    void handleLogin(ActionEvent event) {

        String username = usernameField.getText();
        String password = passwordField.getText();

        try {
            Connection conn = DatabaseConnection.getConnection();

            String sql = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, username);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                messageLabel.setText("Login Successful");
            } else {
                messageLabel.setText("Invalid Username or Password");
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Database Error");
        }
    }

    @FXML
    void handleRegister(ActionEvent event) {

        String username = usernameField.getText();
        String password = passwordField.getText();

        try {
            Connection conn = DatabaseConnection.getConnection();

            String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, username);
            pst.setString(2, password);

            pst.executeUpdate();

            messageLabel.setText("User Registered Successfully");

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Registration Failed");
        }
    }
}