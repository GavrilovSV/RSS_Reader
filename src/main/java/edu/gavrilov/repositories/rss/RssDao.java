package edu.gavrilov.repositories.rss;

import edu.gavrilov.repositories.security.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RssDao {

    @Autowired
    UserDao userDao;

    private static final String DB_URL = "jdbc:postgresql://ec2-54-247-123-231.eu-west-1.compute.amazonaws.com:5432/d39p92kdthmrkj";
    private static final String USERNAME = "kfkejvvdvdbgpg";
    private static final String PASSWORD = "947deb00f0e3e44e80ba73581cff634b7d3192ec9e3b47ded577e86e7f712a8b";

    public List<String> getUrlsList() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        int user_id = userDao.getUserIdByUsername(currentPrincipalName);

        List<String> urls = new ArrayList<>();

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

    public int getChannelIdByUrl(String url) {

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

    public void deleteUrlById(int channel_id) {

        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {

            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM urls WHERE id = \'" + channel_id + "\'");

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }

    }

    public void addUrl(String url) {

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
