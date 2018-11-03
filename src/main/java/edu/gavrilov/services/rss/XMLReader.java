package edu.gavrilov.services.rss;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Сервисный класс для обработки информации в формате XML по ссылке на канал
 * Для реализации функциональность работы с XML используется библиотека rometools
 */
@Service
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
