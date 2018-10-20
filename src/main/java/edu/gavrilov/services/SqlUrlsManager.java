package edu.gavrilov.services;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlUrlsManager implements UrlsManager {

    private final String DB_URL = "jdbc:postgresql://ec2-54-247-123-231.eu-west-1.compute.amazonaws.com:5432/d39p92kdthmrkj";
    private final String USERNAME = "kfkejvvdvdbgpg";
    private final String PASSWORD = "947deb00f0e3e44e80ba73581cff634b7d3192ec9e3b47ded577e86e7f712a8b";

    @Override
    public List<String> getUrlsList() throws IOException {

        List<String> urls = new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT url FROM urls");

            while (rs.next()) {
                String url = rs.getString("url");
                urls.add(url);
            }

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }

        return urls;
    }
}
