package com.tvm.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    public static final String URL = System.getenv("DATABASE_URL");
    public static final String USERNAME = System.getenv("DATABASE_USERNAME");
    public static final String PASSWORD = System.getenv("DATABASE_PASSWORD");

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

}
