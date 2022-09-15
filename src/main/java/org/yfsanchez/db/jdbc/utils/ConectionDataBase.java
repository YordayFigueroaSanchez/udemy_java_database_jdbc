package org.yfsanchez.db.jdbc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectionDataBase {
    private static String url = "jdbc:mysql://localhost:3306/java_curso";
    private static String username = "root";
    private static String password = "geocom";
    private static Connection connection;

    public static Connection getIntance() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

}
