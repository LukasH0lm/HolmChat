package com.lukash0lm.holmchat;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class ConnectionSingleton {

    private static ConnectionSingleton instance;

    private Connection connection;


    private ConnectionSingleton() throws IOException {


        try {

            File myObj = new File(HelloApplication.class.getResource("").getPath() + "DatabaseSettings");
            Scanner myReader = new Scanner(myObj);


            System.out.println(myReader.nextLine()); // start of database settings
            String serverName = myReader.nextLine();
            String databaseName = myReader.nextLine();
            String userName = myReader.nextLine();
            String password = myReader.nextLine();
            System.out.println(myReader.nextLine()); // end of database settings


            myReader.close();


            String url = "jdbc:sqlserver://" + serverName + ":1433;DatabaseName=" + databaseName + ";user=" + userName + ";password=" + password + ";encrypt=false;trustServerCertificate=true;";
            connection = DriverManager.getConnection(url);


        } catch (SQLException e) {
            System.err.println("can not create connection");
            System.out.println(e.getMessage());
        }

        System.out.println("connected to DB");

    }

    public Connection getConnection() {
        return connection;
    }

    public static ConnectionSingleton getInstance() throws SQLException, IOException {

        if (instance == null) {
            instance = new ConnectionSingleton();
        } else if (instance.getConnection().isClosed()) {
            instance = new ConnectionSingleton();
        }

        return instance;
    }


}