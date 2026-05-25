package com.hospital;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hospital_db",
                    "root",
                    "123456789"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}