package de.telekom.jdbcchallenge;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.*;
import java.util.Scanner;

import static java.sql.DriverManager.*;


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
            Connection connection = getConnection(URL);
            Statement statement = connection.createStatement();
     //       PreparedStatement preparedStatement = connection.prepareStatement(String);
            ResultSet resultSet = statement.executeQuery( "select * from personen");
            while(resultSet.next()) {
                System.out.println("ID: "+ resultSet.getLong(1));
                System.out.println("Salutation: "+ resultSet.getShort(2));
                System.out.println("Name: "+ resultSet.getString(3));
                System.out.println("Surname: "+ resultSet.getString(4));
            }
            Scanner scanner = new Scanner(System.in);
            System.out.println("ID: ");
            long id = Long.parseLong(scanner.nextLine());
            System.out.println("Salutation: ");
            short salutation = Short.parseShort(scanner.nextLine());
            System.out.println("Name: ");
            String name = scanner.nextLine();
            System.out.println("Surname: ");
            String surname = scanner.nextLine();
            System.out.println(id + " "+ salutation + " "+name + " "+surname+ " ");

            statement.executeQuery("INSERT INTO personen (ID, SALUTATION, NAME, SURNAME) values (\" + id +\",\" + salutation +\", '\" +name + \"', '\" + surname + \"')");
            resultSet =statement.executeQuery("select * from personen");

            while (resultSet.next()){
                System.out.println("ID: "+ resultSet.getLong(1));
                System.out.println("Salutation: "+ resultSet.getShort(2));
                System.out.println("Name: "+ resultSet.getString(3));
                System.out.println("Surname: "+ resultSet.getString(4));
            }
            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}

