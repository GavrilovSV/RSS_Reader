package edu.gavrilov.services;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import edu.gavrilov.repositories.SqlUpdator;
import edu.gavrilov.rss.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChannelsManagerImpl implements ChannelsManager {

    @Autowired
    SqlUpdator sqlUpdator;

    private List<Channel> channels = new ArrayList<>();
    private List<String> urls = new ArrayList<>();

    private void updateChannelsUrlsList() {

        urls = sqlUpdator.getUrlsList();

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
                if (feed != null) {
                    String imgUrl = null;
                    if (feed.getImage() != null) {
                        imgUrl = feed.getImage().getUrl();
                    }
                    Channel channel = new Channel(imgUrl, feed.getLink(), feed.getTitle(), feed.getDescription());
                    channel.setId(sqlUpdator.getChannelIdByUrl(link));
                    channels.add(channel);
                }
                else
                    continue;
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

        sqlUpdator.addUrl(url);

    }

    @Override
    public void deleteChannel(int channel_id) {

        sqlUpdator.deleteUrlById(channel_id);

    }

}
