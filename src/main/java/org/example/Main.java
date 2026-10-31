package org.example;

import java.sql.Connection;
import java.sql.DriverManager;


public class Main {
    public static void main(String[] args) {

        String url = "jdbc:sqlserver://localhost:1433;databaseName=LibraryDB;encrypt=true;trustServerCertificate=true";
        String username = "YOUR_USERNAME";
        String password = "YOUR_PASSWORD";

        try(Connection con = DriverManager.getConnection(url,username,password)){
            System.out.println("Successfully Connection");
        } catch (Exception e) {
           e.printStackTrace();
        }





    }
}