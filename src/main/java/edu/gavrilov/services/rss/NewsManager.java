package edu.gavrilov.services.rss;

import com.rometools.rome.feed.synd.SyndEnclosure;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.io.FeedException;
import edu.gavrilov.entity.rss.News;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * Класс-сервис для работы со списокм новостей
 */
@Service
public class NewsManager {

    private ChannelsManager channelsManager;
    private XMLReader xmlReader = new XMLReader();

    /**
     * Конструктор класс
     * @param channelsManager - ChannelsManager object
     */
    public NewsManager(ChannelsManager channelsManager) {
        this.channelsManager = channelsManager;
    }

    /**
     * Метод для получения списка новостей
     * @return список новостей
     * @throws IOException
     * @throws FeedException
     */
    public List listNews() throws IOException, FeedException {

        List allNews = new ArrayList();
        List<String> urls;
        urls = channelsManager.getChannelsUrlsList();

        for (int i = 0; i < urls.size(); i++) {
                List news = xmlReader.listNews(urls.get(i));
                allNews.addAll(news);
        }

        try {
            allNews = sortNews(allNews);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        return allNews;
    }

    /**
     * Метод для сортировки агрегированного списка новостей
     * @param listNews - список новостей
     * @return отсортированный список новостей
     */
    private List sortNews(List listNews) {
        List news = new ArrayList();
        Iterator itEntries = listNews.iterator();
        while (itEntries.hasNext()) {
            SyndEntry entry = (SyndEntry) itEntries.next();
            List<SyndEnclosure> list = entry.getEnclosures();
            String pic = null;
            try {
                pic = list.get(0).getUrl();
            } catch (IndexOutOfBoundsException e) {
                //do nothing
             }

            String text = Jsoup.parse(entry.getDescription().getValue()).text();


            news.add(new News(entry.getTitle(),
                    entry.getLink(),
                    pic,
                    text,
                    entry.getPublishedDate().getTime()));
        }
        Collections.sort(news, snorderer);

        return news;
    }

    /**
     * Объект-компаратор для сортироки списка новостей по дате
     */
    static Comparator<News> snorderer = new Comparator<News>() {
        public int compare(News o1, News o2) {
            return Long.valueOf(o2.getTime()).compareTo(Long.valueOf(o1.getTime()));
        }
    };

}
