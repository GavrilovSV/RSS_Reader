package edu.gavrilov.services;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import edu.gavrilov.rss.Channel;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class FileChannelsManager implements ChannelsManager {

    UrlsManager urlsManager;

    private List<Channel> channelsList = new ArrayList<>();

    public FileChannelsManager(UrlsManager urlsManager) {

        this.urlsManager = urlsManager;

        try {

            List<String> urls = urlsManager.getUrlsList();

            SyndFeed feed;

            for (String address: urls) {
                URL url = new URL(address);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                SyndFeedInput input = new SyndFeedInput();
                feed = input.build(new XmlReader(connection));
                Channel channel = new Channel(feed.getImage().getUrl(), feed.getLink(), feed.getTitle(), feed.getDescription());
                channelsList.add(channel);
            }


        } catch (IOException | FeedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Channel> getChannelsList() {
        return channelsList;
    }

    @Override
    public void addChannel(String url) {

    }

    @Override
    public void deleteChannel(int channel_id) {

        int index = 0;

        for (int i = 0; i < channelsList.size(); i++) {
            if (channelsList.get(i).getId() == channel_id) {
                index = i;
                break;
            }
        }

        channelsList.remove(index);

    }

}
