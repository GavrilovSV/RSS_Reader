package edu.gavrilov.repositories;

import edu.gavrilov.security.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlUpdator {

    static UserDao userDao = new UserDao();

    private static final String DB_URL = "jdbc:postgresql://ec2-54-247-123-231.eu-west-1.compute.amazonaws.com:5432/d39p92kdthmrkj";
    private static final String USERNAME = "kfkejvvdvdbgpg";
    private static final String PASSWORD = "947deb00f0e3e44e80ba73581cff634b7d3192ec9e3b47ded577e86e7f712a8b";

    public static List<String> getUrlsList() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        int user_id = userDao.getUserIdByUsername(currentPrincipalName);

        List<String> urls = new ArrayList<>();

/*        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
        }*/

        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT url FROM urls WHERE user_id = " + user_id);

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

    public static int getChannelIdByUrl(String url) {

        int id = 0;

        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, url from urls WHERE url = \'" + url + "\'");

            while (rs.next()) {
                id = rs.getInt("id");
            }

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }

        return id;

    }

    public static void deleteUrlById(int channel_id) {

        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {

            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM urls WHERE id = \'" + channel_id + "\'");

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }

    }

    public static void addUrl(String url) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        int user_id = userDao.getUserIdByUsername(currentPrincipalName);

        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {

            Statement stmt = connection.createStatement();
            stmt.executeUpdate("INSERT INTO urls (url, user_id) VALUES (\'" + url + "\', " + user_id + ")");

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }

    }

}
