package edu.gavrilov.services;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import edu.gavrilov.rss.Channel;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlChannelsManager implements ChannelsManager {

    private List<Channel> channels = new ArrayList<>();
    private List<String> urls = new ArrayList<>();

    private final String DB_URL = "jdbc:postgresql://ec2-54-247-123-231.eu-west-1.compute.amazonaws.com:5432/d39p92kdthmrkj";
    private final String USERNAME = "kfkejvvdvdbgpg";
    private final String PASSWORD = "947deb00f0e3e44e80ba73581cff634b7d3192ec9e3b47ded577e86e7f712a8b";

    private void updateChannelsUrlsList() {

        urls = new ArrayList<>();

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


    }

    private void updateChannelsList() {

        updateChannelsUrlsList();

        channels = new ArrayList<>();

        try {

            SyndFeed feed;

            for (String link: urls) {
                URL url = new URL(link);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                SyndFeedInput input = new SyndFeedInput();
                feed = input.build(new XmlReader(connection));
                Channel channel = new Channel(feed.getImage().getUrl(), feed.getLink(), feed.getTitle(), feed.getDescription());
                channels.add(channel);
            }

        } catch (IOException | FeedException e) {
            e.printStackTrace();
        }


    }

    @Override
    public List<String> getChannelsUrlsList() {

        updateChannelsUrlsList();
        return urls;

    }

    @Override
    public List<Channel> getChannelsList() {

        updateChannelsList();
        return channels;

    }

    @Override
    public void addChannel(String url) {

    }

    @Override
    public void deleteChannel(int channel_id) {

    }

}
