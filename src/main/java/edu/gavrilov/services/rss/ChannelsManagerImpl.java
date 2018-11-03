package edu.gavrilov.services.rss;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import edu.gavrilov.repositories.rss.RssDao;
import edu.gavrilov.entity.rss.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Сервисный класс
 * Реализация интерфейса ChannelsManager
 */
@Service
public class ChannelsManagerImpl implements ChannelsManager {

    @Autowired
    RssDao rssDao;

    private List<Channel> channels = new ArrayList<>();
    private List<String> urls = new ArrayList<>();

    private void updateChannelsUrlsList() {

        urls = rssDao.getUrlsList();

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
                    channel.setId(rssDao.getChannelIdByUrl(link));
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

        rssDao.addUrl(url);

    }

    @Override
    public void deleteChannel(int channel_id) {

        rssDao.deleteUrlById(channel_id);

    }

}
