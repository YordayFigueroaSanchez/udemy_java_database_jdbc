package org.yfsanchez.db.jdbc;

import org.yfsanchez.db.jdbc.utils.ConectionDataBase;

import java.sql.*;

public class EjemploJdbc {
    public static void main(String[] args) {

        try (Connection connection = ConectionDataBase.getIntance();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from productos")) {

            while (resultSet.next()){
                System.out.print(resultSet.getInt("id"));
                System.out.print(" | ");
                System.out.print(resultSet.getString("nombre"));
                System.out.print(" | ");
                System.out.print(resultSet.getInt("precio"));
                System.out.print(" | ");
                System.out.println(resultSet.getDate("fecha"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
