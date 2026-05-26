package com.hospital;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://zephyr.proxy.rlwy.net:27197/railway",
                    "root",
                    "oYDbmIFdVWnhPYLroWPTNVeLHlyhJSkJ"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}