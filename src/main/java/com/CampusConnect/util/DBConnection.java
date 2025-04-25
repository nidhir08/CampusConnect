//package com.CampusConnect.util;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class DBConnection {
//    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/CampusConnect";
//    private static final String JDBC_USER = "root";
//    private static final String JDBC_PASSWORD = "hello123"; // ✅ Change this to your actual MySQL password
//
//    // Static block to load JDBC driver
//    static {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            System.out.println("✅ JDBC Driver loaded successfully.");
//        } catch (ClassNotFoundException e) {
//            System.err.println("❌ Failed to load JDBC Driver.");
//            e.printStackTrace();
//        }
//    }
//
//    // Method to establish and return connection
//    public static Connection getConnection() {
//        Connection conn = null;
//        try {
//            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
//            System.out.println("✅ Connected to DB");
//        } catch (SQLException e) {
//            System.err.println("❌ DB Connection Failed");
//            e.printStackTrace();
//        }
//        return conn;
//    }
//
//
//    // Method to safely close connection
//    public static void closeConnection(Connection connection) {
//        if (connection != null) {
//            try {
//                connection.close();
//                System.out.println("🔌 Database connection closed.");
//            } catch (SQLException e) {
//                System.err.println("❌ Error closing the connection.");
//                e.printStackTrace();
//            }
//        }
//    }
//}

package com.CampusConnect.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/CampusConnect";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = ""; // ✅ Change this to your actual MySQL password

    // Static block to load JDBC driver
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ JDBC Driver loaded successfully.");
        } catch (ClassNotFoundException e) {
            System.err.println("❌ Failed to load JDBC Driver.");
            e.printStackTrace();
        }
    }

    // Method to establish and return connection
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            System.out.println("✅ Connected to DB");
        } catch (SQLException e) {
            System.err.println("❌ DB Connection Failed");
            e.printStackTrace();
        }
        return conn;
    }


    // Method to safely close connection
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("🔌 Database connection closed.");
            } catch (SQLException e) {
                System.err.println("❌ Error closing the connection.");
                e.printStackTrace();
            }
        }
    }
}