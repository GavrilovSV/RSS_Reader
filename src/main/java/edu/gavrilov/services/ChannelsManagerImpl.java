package edu.gavrilov.services;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import edu.gavrilov.repositories.SqlUpdator;
import edu.gavrilov.rss.Channel;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ChannelsManagerImpl implements ChannelsManager {

    private List<Channel> channels = new ArrayList<>();
    private List<String> urls = new ArrayList<>();

    private final String DB_URL = "jdbc:postgresql://ec2-54-247-123-231.eu-west-1.compute.amazonaws.com:5432/d39p92kdthmrkj";
    private final String USERNAME = "kfkejvvdvdbgpg";
    private final String PASSWORD = "947deb00f0e3e44e80ba73581cff634b7d3192ec9e3b47ded577e86e7f712a8b";

    private void updateChannelsUrlsList() {

        urls = SqlUpdator.getUrlsList();

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
                channel.setId(SqlUpdator.getChannelIdByUrl(link));
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
    public Channel getChannelById(int channel_id) {

        Channel found = null;
        updateChannelsList();

        for (Channel channel: channels) {
            if (channel.getId() == channel_id) {
                found = channel;
            }
        }

        return found;
    }

    @Override
    public void addChannel(String url) {

    }

    @Override
    public void deleteChannel(int channel_id) {

        SqlUpdator.deleteUrlById(channel_id);

    }

}
