package edu.gavrilov.xml;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class XMLReader {

    public List listNews(String address) throws IOException, FeedException {

        SyndFeed feed;
        URL url = new URL(address);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        SyndFeedInput input = new SyndFeedInput();
        feed = input.build(new XmlReader(connection));
        return feed.getEntries();

    }

}
