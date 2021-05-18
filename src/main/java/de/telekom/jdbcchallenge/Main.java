package de.telekom.jdbcchallenge;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.*;


public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        final String DRIVER = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        final String URL = "jdbc:mysql://localhost:3306/seadb?user=seauser&password=seapass";
        try {
            Connection connection = DriverManager.getConnection(URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery( "select * from persons");
            while(resultSet.next()) {
                System.out.println(resultSet.getInt(1));
                System.out.println(resultSet.getString(2));
                System.out.println(resultSet.getString(3));
                System.out.println(resultSet.getString(4));
            }
            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}

